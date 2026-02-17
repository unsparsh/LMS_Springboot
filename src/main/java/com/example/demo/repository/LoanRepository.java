package com.example.demo.repository;

import com.example.demo.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByMemberId(Long memberId);
    Optional<Loan> findByRequestId(String requestId);
    List<Loan> findByDueDateBeforeAndReturnDateIsNull(LocalDate date);
}
