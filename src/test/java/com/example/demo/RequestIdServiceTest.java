package com.example.demo;

import com.example.demo.entity.RequestSequence;
import com.example.demo.repository.RequestSequenceRepository;
import com.example.demo.service.RequestIdService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RequestIdServiceTest {
    @Test
    void shouldGenerateId() {
        RequestSequenceRepository repo = mock(RequestSequenceRepository.class);
        RequestSequence seq = new RequestSequence();
        seq.setYear(2026);
        seq.setNextRunningNumber(1);
        when(repo.findById(anyInt())).thenReturn(Optional.of(seq));
        when(repo.saveAndFlush(any())).thenAnswer(inv -> inv.getArgument(0));
        RequestIdService service = new RequestIdService(repo);
        String id = service.nextRequestId();
        assertTrue(id.startsWith("LMS-"));
    }
}
