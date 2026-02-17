package com.example.demo;

import com.example.demo.controller.LoanController;
import com.example.demo.dto.LoanDtos;
import com.example.demo.service.LoanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoanController.class)
@AutoConfigureMockMvc(addFilters = false)
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    @Test
    void shouldStartBorrowProcess() throws Exception {
        when(loanService.borrow(any())).thenReturn(new LoanDtos.ProcessStartResponse("LMS-2026-0001", 1001L));

        mockMvc.perform(post("/api/v1/loans/borrow")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"memberId\":1,\"bookId\":1}"))
                .andExpect(status().isAccepted());
    }
}
