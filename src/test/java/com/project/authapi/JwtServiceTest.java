package com.project.authapi;

import com.project.authapi.security.JwtService;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class JwtServiceTest {

    @Autowired JwtService jwtService;

    @Test
    void generate_then_parse_returns_same_userId() {
        String token = jwtService.generateToken("user-abc-123");
        Claims claims = jwtService.parseAndValidate(token);
        assertThat(claims.getSubject()).isEqualTo("user-abc-123");
    }

    @Test
    void parse_throws_for_garbage_token() {
        assertThatThrownBy(() -> jwtService.parseAndValidate("not.a.token"))
                .isInstanceOf(io.jsonwebtoken.JwtException.class);
    }
}
