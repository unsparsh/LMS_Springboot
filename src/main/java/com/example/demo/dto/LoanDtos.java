package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class LoanDtos {
    public record BorrowRequest(@NotNull Long memberId, @NotNull Long bookId) {}
    public record ReturnRequest(@NotNull Long memberId, @NotNull Long bookId) {}
    public record ExtendRequest(@NotNull Long memberId, @NotNull Long loanId, @Min(1) int requestedDays) {}
    public record LoanResponse(Long loanId, String requestId, String status, LocalDate borrowDate, LocalDate dueDate, LocalDate returnDate) {}
    public record ProcessStartResponse(String requestId, Long processInstanceId) {}
    public record OverdueLoanResponse(Long loanId, String requestId, LocalDate dueDate, BigDecimal estimatedFine) {}
    public record InternalReserveResponse(LocalDate dueDate, String reservationStatus) {}
    public record InternalReturnResponse(BigDecimal fineAmount, String finalStatus) {}
    public record TaskCompleteRequest(Map<String,Object> variables) {}
}
