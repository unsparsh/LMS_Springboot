package com.example.demo.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CamundaProcessService {
    private final RestClient camundaRestClient;

    public CamundaProcessService(RestClient camundaRestClient) {
        this.camundaRestClient = camundaRestClient;
    }

    public Map<String, Object> startProcess(String bpmnProcessId, String businessKey, Map<String, Object> variables, boolean awaitResult) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("processDefinitionId", bpmnProcessId);
        payload.put("businessKey", businessKey);
        payload.put("variables", variables);
        payload.put("awaitCompletion", awaitResult);
        return camundaRestClient.post().uri("/process-instances")
                .contentType(MediaType.APPLICATION_JSON)
                .body(payload)
                .retrieve()
                .body(Map.class);
    }

    public Map<String, Object> getInstanceStatusByBusinessId(String requestId) {
        return camundaRestClient.get().uri("/process-instances?businessKey=" + requestId)
                .retrieve().body(Map.class);
    }

    public void deployResources() {
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:processes/*.bpmn");
            deployResources(List.of(resources));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void deployResources(List<Resource> resources) {
        for (Resource ignored : resources) {
            // Placeholder: in production, use Camunda deployment endpoint multipart request.
        }
    }
}
