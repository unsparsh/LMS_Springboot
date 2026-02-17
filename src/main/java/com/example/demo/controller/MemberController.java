package com.example.demo.controller;

import com.example.demo.dto.LoanDtos;
import com.example.demo.dto.MemberDtos;
import com.example.demo.service.LoanService;
import com.example.demo.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MemberController {
    private final MemberService memberService;
    private final LoanService loanService;

    public MemberController(MemberService memberService, LoanService loanService) {
        this.memberService = memberService;
        this.loanService = loanService;
    }

    @PostMapping("/members") @ResponseStatus(HttpStatus.ACCEPTED)
    public MemberDtos.MemberResponse register(@Valid @RequestBody MemberDtos.MemberCreateRequest request) { return memberService.register(request); }
    @GetMapping("/members/{memberId}")
    public MemberDtos.MemberResponse profile(@PathVariable Long memberId) { return memberService.get(memberId); }
    @GetMapping("/members/{memberId}/loans")
    public List<LoanDtos.LoanResponse> loans(@PathVariable Long memberId) { return loanService.history(memberId); }
}
