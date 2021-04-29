package nl.bartbouman.sentimentbot.backend.controller;

import com.theokanning.openai.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import nl.bartbouman.sentimentbot.backend.domain.Request;
import nl.bartbouman.sentimentbot.backend.domain.Response;
import nl.bartbouman.sentimentbot.backend.repository.ResponsesRepository;
import nl.bartbouman.sentimentbot.backend.utility.GetPropertiesBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.OffsetDateTime;

import static nl.bartbouman.sentimentbot.backend.data.Sentences.getBasePrompt;
import static nl.bartbouman.sentimentbot.backend.data.Sentences.getPracticePrompt;
import static nl.bartbouman.sentimentbot.backend.utility.CompletionRequests.getCompletionRequest;
import static nl.bartbouman.sentimentbot.backend.utility.GetPropertiesBean.OPENAI_TOKEN;

@RestController
@RequestMapping("/api/v1/sentiment/")
@Slf4j
public class Controller {

    @Autowired
    private ResponsesRepository responsesRepository;

    @PostMapping("fromSentence")
    public ResponseEntity<Response> getSentimentFromSentence(@RequestBody Request request) throws URISyntaxException {
        OpenAiService service = new OpenAiService(OPENAI_TOKEN);

        String prompt = getPracticePrompt + getBasePrompt(request.getSentence());

        String sentiment = getSentimentFromCompletionResponse(
                service.createCompletion("davinci", getCompletionRequest(prompt))
                        .getChoices().get(0).getText(),
                prompt);

        Response response = Response.builder()
                .timestamp(OffsetDateTime.now())
                .sentence(request.getSentence())
                .sentiment(sentiment)
                .build();
        responsesRepository.save(response);

        return ResponseEntity.created(new URI("/" + response.getId())).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> findResponse(@PathVariable(value = "id") Long responseId) {
        Response response = responsesRepository.findById(responseId)
                .orElseThrow(() -> {
                    log.info("Could not find response with ID: " + responseId);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND);
                });

        return ResponseEntity.ok(response);
    }

    private String getSentimentFromCompletionResponse(String responseBody, String prompt) {
        return responseBody.replace(prompt, "").trim();
    }

}
