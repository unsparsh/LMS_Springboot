package com.example.demo.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class TasklistService {
    private final RestClient camundaRestClient;

    public TasklistService(RestClient camundaRestClient) {this.camundaRestClient = camundaRestClient;}

    public Object searchTasks(String processKey, String assignee, String state) {
        return camundaRestClient.get().uri("/tasklist/tasks?processDefinitionKey=" + processKey + "&assignee=" + assignee + "&state=" + state)
                .retrieve().body(Object.class);
    }
    public void claim(String taskId, String assignee) {
        camundaRestClient.post().uri("/tasklist/tasks/" + taskId + "/assign").contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("assignee", assignee)).retrieve().toBodilessEntity();
    }
    public void unassign(String taskId) {
        camundaRestClient.post().uri("/tasklist/tasks/" + taskId + "/unassign").retrieve().toBodilessEntity();
    }
    public void complete(String taskId, Map<String,Object> variables) {
        camundaRestClient.post().uri("/tasklist/tasks/" + taskId + "/complete").contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("variables", variables)).retrieve().toBodilessEntity();
    }
}
