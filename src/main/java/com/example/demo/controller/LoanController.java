package com.example.demo.controller;

import com.example.demo.dto.LoanDtos;
import com.example.demo.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {
    private final LoanService loanService;
    public LoanController(LoanService loanService) { this.loanService = loanService; }

    @PostMapping("/borrow") @ResponseStatus(HttpStatus.ACCEPTED)
    public LoanDtos.ProcessStartResponse borrow(@Valid @RequestBody LoanDtos.BorrowRequest request){ return loanService.borrow(request); }
    @PostMapping("/return") @ResponseStatus(HttpStatus.ACCEPTED)
    public LoanDtos.ProcessStartResponse returnBook(@Valid @RequestBody LoanDtos.ReturnRequest request){ return loanService.returnBook(request); }
    @PostMapping("/extend") @ResponseStatus(HttpStatus.ACCEPTED)
    public LoanDtos.ProcessStartResponse extend(@Valid @RequestBody LoanDtos.ExtendRequest request){ return loanService.extendLoan(request); }
}
