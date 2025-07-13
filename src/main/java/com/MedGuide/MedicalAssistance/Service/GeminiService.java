package com.MedGuide.MedicalAssistance.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeminiService {

    private final WebClient geminiWebClient;

    @Value("${gemini.api-key}")
    private String apiKey;

    @Value("${gemini.model}")
    private String model;

    public String ask(String prompt) {
        try {
            String jsonBody = String.format("""
                {
                  "contents": [{
                    "parts": [{ "text": "%s" }]
                  }]
                }
                """, prompt.replace("\"", "\\\""));

            JsonNode response = geminiWebClient.post()
                    .uri("/models/" + model + ":generateContent?key=" + apiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(jsonBody)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            return response.at("/candidates/0/content/parts/0/text")
                    .asText("⚠️ Gemini returned no text");

        } catch (Exception e) {
            return "⚠️ Gemini error: " + e.getMessage();
        }
    }
}
