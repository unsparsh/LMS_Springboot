package com.example.demo.service;

import com.example.demo.dto.AuthDtos;
import com.example.demo.entity.Role;
import com.example.demo.entity.UserAccount;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public void createDevUsersIfMissing() {
        createIfMissing("librarian@test", "password", Role.ROLE_LIBRARIAN);
        createIfMissing("member@test", "password", Role.ROLE_MEMBER);
    }

    private void createIfMissing(String username, String password, Role role) {
        if (userRepository.findByUsername(username).isEmpty()) {
            UserAccount u = new UserAccount();
            u.setUsername(username);
            u.setPassword(passwordEncoder.encode(password));
            u.setRole(role);
            userRepository.save(u);
        }
    }

    public AuthDtos.LoginResponse login(AuthDtos.LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        return new AuthDtos.LoginResponse(jwtUtil.generateToken(request.username()));
    }
}
