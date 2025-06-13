package com.hei.school.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HazavaoService {

    private String apiKey = "api_key";

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public String getDefinitionInMalagasy(String word) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", "Hazavao amin'ny teny malagasy tsotra ny teny : " + word);

        Map<String, Object> request = new HashMap<>();
        request.put("model", "gpt-3.5-turbo");
        request.put("messages", List.of(message));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(API_URL, entity, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
            Map<String, Object> messageResponse = (Map<String, Object>) choices.get(0).get("message");
            return (String) messageResponse.get("content");
        } else {
            return "Tsy afaka mahazo valiny amin'izao fotoana izao.";
        }
    }
}