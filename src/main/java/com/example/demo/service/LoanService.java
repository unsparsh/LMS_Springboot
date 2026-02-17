package com.example.demo.service;

import com.example.demo.dto.LoanDtos;
import com.example.demo.entity.Loan;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.LoanRepository;
import com.example.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final RequestIdService requestIdService;
    private final CamundaProcessService processService;

    public LoanService(LoanRepository loanRepository, MemberRepository memberRepository, BookRepository bookRepository, RequestIdService requestIdService, CamundaProcessService processService) {
        this.loanRepository = loanRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.requestIdService = requestIdService;
        this.processService = processService;
    }

    @Transactional
    public LoanDtos.ProcessStartResponse borrow(LoanDtos.BorrowRequest req) {
        String requestId = requestIdService.nextRequestId();
        Loan loan = new Loan();
        loan.setMember(memberRepository.findById(req.memberId()).orElseThrow());
        loan.setBook(bookRepository.findById(req.bookId()).orElseThrow());
        loan.setRequestId(requestId);
        loan.setStatus("PENDING_BORROW");
        loanRepository.save(loan);
        Map<String, Object> process = processService.startProcess("BorrowBook", requestId, Map.of("requestId", requestId, "memberId", req.memberId(), "bookId", req.bookId()), false);
        return new LoanDtos.ProcessStartResponse(requestId, process == null ? null : (Long) process.getOrDefault("processInstanceKey", null));
    }

    public List<LoanDtos.LoanResponse> history(Long memberId) {
        return loanRepository.findByMemberId(memberId).stream().map(l -> new LoanDtos.LoanResponse(l.getId(), l.getRequestId(), l.getStatus(), l.getBorrowDate(), l.getDueDate(), l.getReturnDate())).toList();
    }

    public LoanDtos.ProcessStartResponse returnBook(LoanDtos.ReturnRequest req) {
        String requestId = requestIdService.nextRequestId();
        Map<String, Object> process = processService.startProcess("ReturnBook", requestId, Map.of("requestId", requestId, "memberId", req.memberId(), "bookId", req.bookId()), false);
        return new LoanDtos.ProcessStartResponse(requestId, process == null ? null : (Long) process.getOrDefault("processInstanceKey", null));
    }

    public LoanDtos.ProcessStartResponse extendLoan(LoanDtos.ExtendRequest req) {
        String requestId = requestIdService.nextRequestId();
        Map<String, Object> process = processService.startProcess("ExtendLoan", requestId, Map.of("requestId", requestId, "memberId", req.memberId(), "loanId", req.loanId(), "requestedDays", req.requestedDays()), false);
        return new LoanDtos.ProcessStartResponse(requestId, process == null ? null : (Long) process.getOrDefault("processInstanceKey", null));
    }

    public List<LoanDtos.OverdueLoanResponse> overdueLoans() {
        return loanRepository.findByDueDateBeforeAndReturnDateIsNull(LocalDate.now()).stream().map(l ->
                new LoanDtos.OverdueLoanResponse(l.getId(), l.getRequestId(), l.getDueDate(), BigDecimal.valueOf(10))
        ).toList();
    }
}
