package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class BookDtos {
    public record BookRequest(@NotBlank String title, @NotBlank String author, @NotBlank String isbn, Long categoryId,
                              @Min(1) Integer copiesTotal, @Min(0) Integer copiesAvailable) {}
    public record BookResponse(Long id, String title, String author, String isbn, Long categoryId, Integer copiesTotal, Integer copiesAvailable) {}
}
