package com.example.demo.service;

import com.example.demo.dto.MemberDtos;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final CamundaProcessService camundaProcessService;

    public MemberService(MemberRepository memberRepository, CamundaProcessService camundaProcessService) {
        this.memberRepository = memberRepository;
        this.camundaProcessService = camundaProcessService;
    }

    @Transactional
    public MemberDtos.MemberResponse register(MemberDtos.MemberCreateRequest req) {
        Member m = new Member();
        m.setName(req.name());
        m.setEmail(req.email());
        m.setSubscriptionExpiry(LocalDate.now().plusYears(1));
        m.setStatus("ACTIVE");
        m = memberRepository.save(m);
        camundaProcessService.startProcess("OnboardMember", "MEM-" + m.getId(), Map.of("memberId", m.getId(), "email", m.getEmail()), false);
        return new MemberDtos.MemberResponse(m.getId(), m.getName(), m.getEmail(), m.getSubscriptionExpiry(), m.getStatus());
    }

    public MemberDtos.MemberResponse get(Long id) {
        Member m = memberRepository.findById(id).orElseThrow();
        return new MemberDtos.MemberResponse(m.getId(), m.getName(), m.getEmail(), m.getSubscriptionExpiry(), m.getStatus());
    }
}
