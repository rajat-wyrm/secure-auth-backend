package com.project.authapi;

import com.project.authapi.security.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class AuthapiApplicationTests {

    @Autowired JwtService jwtService;

    @Test
    void contextLoads() {
    }

    @Test
    void jwtServiceBeanIsWired() {
        org.assertj.core.api.Assertions.assertThat(jwtService).isNotNull();
    }
}
