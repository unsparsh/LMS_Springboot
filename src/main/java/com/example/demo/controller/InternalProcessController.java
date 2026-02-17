package com.example.demo.controller;

import com.example.demo.dto.LoanDtos;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/internal/processes")
public class InternalProcessController {
    @PostMapping("/borrow/{requestId}/reserve")
    public LoanDtos.InternalReserveResponse reserve(@PathVariable String requestId) {
        return new LoanDtos.InternalReserveResponse(LocalDate.now().plusDays(14), "RESERVED");
    }

    @PostMapping("/return/{requestId}/settle")
    public LoanDtos.InternalReturnResponse settle(@PathVariable String requestId) {
        return new LoanDtos.InternalReturnResponse(BigDecimal.ZERO, "CLOSED");
    }
}
