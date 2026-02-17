package com.example.demo.dto;

public class SignupResponse {

    private final String message;
    private final Long processInstanceKey;

    public SignupResponse(String message, Long processInstanceKey) {
        this.message = message;
        this.processInstanceKey = processInstanceKey;
    }

    public String getMessage() {
        return message;
    }

    public Long getProcessInstanceKey() {
        return processInstanceKey;
    }
}
