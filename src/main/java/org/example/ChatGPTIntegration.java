package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import java.io.IOException;

public class ChatGPTIntegration {
    private static final String OPENAI_API_KEY = "YOUR_API_KEY";
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/engines/davinci/completions";

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Compose the API request payload
        String inputPrompt = "What is the meaning of life?";
        String payload = "{\"prompt\": \"" + inputPrompt + "\", \"max_tokens\": 50}";

        // Create the HTTP request
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), payload);
        Request request = new Request.Builder()
                .url(OPENAI_API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + "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")
                .build();

        // Send the request and retrieve the response
        Response response = client.newCall(request).execute();
        System.out.println(response);
        String responseBody = response.body().string();

        // Process the response
        // Assuming you have a JSON parsing library like Jackson or Gson, you can parse the response JSON
        // and extract the generated text from the 'choices' field
        // For example, using Jackson:
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(responseBody);
        JsonNode rootNode = mapper.readTree(responseBody);
        System.out.println("Generated Text: " + rootNode.get("choices"));
        String generatedText = rootNode.get("choices").get(0).get("text").asText();

        System.out.println("Generated Text: " + generatedText);
    }
}
