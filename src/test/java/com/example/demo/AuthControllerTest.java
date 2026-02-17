package com.example.demo;

import com.example.demo.controller.AuthController;
import com.example.demo.dto.AuthDtos;
import com.example.demo.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)

@AutoConfigureMockMvc(addFilters = false)

class AuthControllerTest {
    @Autowired MockMvc mockMvc;
    @MockBean AuthService authService;

    @Test
    void login() throws Exception {
        when(authService.login(any())).thenReturn(new AuthDtos.LoginResponse("token"));
        mockMvc.perform(post("/api/v1/auth/login").contentType(MediaType.APPLICATION_JSON).content("{\"username\":\"u\",\"password\":\"p\"}"))
                .andExpect(status().isOk());
    }
}
