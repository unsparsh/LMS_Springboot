package com.example.demo.audit;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class AuditLoggingFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(AuditLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestId = UUID.randomUUID().toString();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principal = authentication == null ? "anonymous" : authentication.getName();
        log.info("audit requestId={} method={} path={} principal={}", requestId, request.getMethod(), request.getRequestURI(), principal);
        filterChain.doFilter(request, response);
    }
}
