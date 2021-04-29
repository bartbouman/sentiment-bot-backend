package nl.bartbouman.sentimentbot.backend.utility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetPropertiesBean {

    public static String OPENAI_TOKEN;

    @Autowired
    public GetPropertiesBean(@Value("${openai.token}") String openai_token) {
        OPENAI_TOKEN = openai_token;
        log.info("OpenAI Token = " + openai_token);
    }

}