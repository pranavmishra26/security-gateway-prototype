package com.gateway.security_gateway.service;
import com.gateway.security_gateway.dto.CheckResponse;
import com.gateway.security_gateway.model.DecisionLog;
import com.gateway.security_gateway.repository.DecisionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import java.time.Instant;
import java.util.Map;
@Service
public class GatewayService {
    @Autowired
    private DecisionLogRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String ML_SERVICE_URL = "http://localhost:8000/predict";
    public CheckResponse evaluate(String content) {
        String decision;
        String source;
        try {
            Map<String, Object> response = restTemplate.postForObject(
                    ML_SERVICE_URL, Map.of("content", content), Map.class);
            decision = (String) response.get("decision");
            source = "ml-service";
        } catch (RestClientException e) {
            decision = fallbackCheck(content);
            source = "fallback-mock";
        }
        DecisionLog log = new DecisionLog(content, decision, source, Instant.now());
        repository.save(log);
        return new CheckResponse(decision, source);
    }
    private String fallbackCheck(String content) {
        String lower = content.toLowerCase();
        if (lower.contains("ignore previous instructions") ||
                lower.contains("system prompt") ||
                lower.contains("reveal your instructions")) {
            return "BLOCK";
        }
        return "ALLOW";
    }
}