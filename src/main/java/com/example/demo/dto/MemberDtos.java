package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public class MemberDtos {
    public record MemberCreateRequest(@NotBlank String name, @Email String email) {}
    public record MemberResponse(Long id, String name, String email, LocalDate subscriptionExpiry, String status) {}
}
