package com.example.demo;

import com.example.demo.controller.BookController;
import com.example.demo.dto.BookDtos;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@AutoConfigureMockMvc(addFilters = false)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void shouldListBooks() throws Exception {
        when(bookService.list(any(), any(), any())).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/books"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateBook() throws Exception {
        when(bookService.create(any())).thenReturn(new BookDtos.BookResponse(1L, "t", "a", "i", null, 2, 2));

        mockMvc.perform(post("/api/v1/admin/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Clean Code\",\"author\":\"Uncle Bob\",\"isbn\":\"1\",\"copiesTotal\":2,\"copiesAvailable\":2}"))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldDeleteBook() throws Exception {
        doNothing().when(bookService).delete(eq(1L));

        mockMvc.perform(delete("/api/v1/admin/books/1"))
                .andExpect(status().isNoContent());
    }
}
