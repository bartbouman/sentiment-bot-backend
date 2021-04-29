package nl.bartbouman.sentimentbot.backend.utility;

import com.theokanning.openai.completion.CompletionRequest;

/**
 * In this class it is possible to fine tune the GPT-3 interaction.
 */
public class CompletionRequests {

    /**
     * CompletionRequest builder.
     *
     * @param prompt The sentence you would like to get the sentiment from
     * @return CompletianRequest all set to find out the sentiment in a sentence!
     */
    public static CompletionRequest getCompletionRequest(String prompt) {
        return CompletionRequest.builder()
                .prompt(prompt)
                .temperature(0.0)
                .maxTokens(1)
                .topP(1.0)
                .frequencyPenalty(0.0)
                .presencePenalty(0.0)
                .echo(true)
                .build();
    }

}
