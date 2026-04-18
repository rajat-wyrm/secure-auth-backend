package com.project.authapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired UserRepository userRepository;
    @Autowired PasswordEncoder passwordEncoder;

    @BeforeEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    void save_then_find_by_username_returns_user() {
        User u = new User("dave", passwordEncoder.encode("DavePass123!"));
        userRepository.save(u);

        Optional<User> found = userRepository.findByUsername("dave");
        assertThat(found).isPresent();
        assertThat(passwordEncoder.matches("DavePass123!", found.get().getPassword())).isTrue();
    }

    @Test
    void exists_by_username_returns_true_for_existing_user() {
        userRepository.save(new User("erin", passwordEncoder.encode("ErinPass123!")));
        assertThat(userRepository.existsByUsername("erin")).isTrue();
    }

    @Test
    void find_by_username_returns_empty_for_missing() {
        assertThat(userRepository.findByUsername("nobody")).isEmpty();
    }
}
