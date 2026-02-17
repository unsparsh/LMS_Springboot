package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${security.jwt.secret}")
    private String secret;
    @Value("${security.jwt.ttl-minutes:120}")
    private long ttlMinutes;

    public String generateToken(String username) {
        return Jwts.builder().setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ttlMinutes * 60_000))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(secret.getBytes(StandardCharsets.UTF_8)).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}
