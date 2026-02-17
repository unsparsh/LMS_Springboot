package com.example.demo.dto;

public class TaskDtos {
    public record TaskSummary(String id, String name, String assignee, String state, String processDefinitionKey) {}
    public record TaskActionRequest(String assignee) {}
}
