package com.example.demo.service;

import com.example.demo.entity.RequestSequence;
import com.example.demo.repository.RequestSequenceRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
public class RequestIdService {
    private final RequestSequenceRepository repository;
    public RequestIdService(RequestSequenceRepository repository) { this.repository = repository; }

    @Transactional
    public String nextRequestId() {
        int year = Year.now().getValue();
        for (int i = 0; i < 3; i++) {
            try {
                RequestSequence seq = repository.findById(year).orElseGet(() -> {
                    RequestSequence s = new RequestSequence();
                    s.setYear(year);
                    s.setNextRunningNumber(1);
                    return s;
                });
                int current = seq.getNextRunningNumber();
                seq.setNextRunningNumber(current + 1);
                repository.saveAndFlush(seq);
                return "LMS-" + year + "-" + String.format("%04d", current);
            } catch (OptimisticLockingFailureException ignored) { }
        }
        throw new IllegalStateException("Could not generate requestId after retries");
    }
}
