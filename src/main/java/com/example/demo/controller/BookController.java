package com.example.demo.controller;

import com.example.demo.dto.BookDtos;
import com.example.demo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<BookDtos.BookResponse> books(@RequestParam(required = false) String author,
                                             @RequestParam(required = false) Long categoryId,
                                             @RequestParam(required = false) Boolean availableOnly) {
        return bookService.list(author, categoryId, availableOnly);
    }

    @PostMapping("/admin/books")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDtos.BookResponse create(@Valid @RequestBody BookDtos.BookRequest request) {
        return bookService.create(request);
    }

    @PutMapping("/admin/books/{id}")
    public BookDtos.BookResponse update(@PathVariable Long id, @Valid @RequestBody BookDtos.BookRequest request) {
        return bookService.update(id, request);
    }

    @PatchMapping("/admin/books/{id}")
    public BookDtos.BookResponse patch(@PathVariable Long id, @Valid @RequestBody BookDtos.BookPatchRequest request) {
        return bookService.patch(id, request);
    }

    @DeleteMapping("/admin/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
