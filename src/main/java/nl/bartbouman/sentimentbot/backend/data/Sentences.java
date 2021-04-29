package nl.bartbouman.sentimentbot.backend.data;

public class Sentences {

    public static String getBasePrompt(String sentence) {
        return "Sentence from the request: \"" + sentence + "\" Sentiment (positive, neutral, negative):";
    }

    public static String getPracticePrompt =
            getBasePrompt("is was bad") + "negative" +
                    getBasePrompt("is was good") + "positive" +
                    getBasePrompt("is was ok") + "neutral" +
                    getBasePrompt("it was average") + "neutral" +
                    getBasePrompt("it was really average") + "neutral";

}
