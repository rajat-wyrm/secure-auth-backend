package com.project.authapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.authapi.dto.AuthResponse;
import com.project.authapi.dto.LoginRequest;
import com.project.authapi.dto.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerTest {

    @Autowired MockMvc mvc;
    @Autowired UserRepository userRepository;
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired ObjectMapper mapper;

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    void register_then_login_round_trip_returns_jwt() throws Exception {
        var regBody = mapper.writeValueAsString(
                new RegisterRequest("alice_test", "Sup3rSecret!"));

        MvcResult regResult = mvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(regBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.userId").exists())
                .andReturn();

        var token = mapper.readValue(regResult.getResponse().getContentAsString(),
                AuthResponse.class).token();
        assertThat(token).isNotBlank();

        var loginBody = mapper.writeValueAsString(
                new LoginRequest("alice_test", "Sup3rSecret!"));

        mvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void login_with_wrong_password_returns_401() throws Exception {
        userRepository.save(new User("bob", passwordEncoder.encode("right-pass-1234")));

        var body = mapper.writeValueAsString(new LoginRequest("bob", "wrong-pass-5678"));

        mvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void register_with_short_password_returns_400() throws Exception {
        var body = mapper.writeValueAsString(new RegisterRequest("eve", "short"));

        mvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void register_with_duplicate_username_returns_409() throws Exception {
        userRepository.save(new User("carol", passwordEncoder.encode("CarolPass123!")));

        var body = mapper.writeValueAsString(
                new RegisterRequest("carol", "AnotherPass123!"));

        mvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isConflict());
    }
}
