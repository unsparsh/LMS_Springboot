package com.example.demo.security;

import com.example.demo.repository.UserRepository;
<<<<<<< HEAD
=======

import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> main
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
<<<<<<< HEAD
=======
	@Autowired
>>>>>>> main
    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.withUsername(u.getUsername()).password(u.getPassword()).authorities(u.getRole().name()).build();
    }
}
