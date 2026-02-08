package com.project.authapi;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("/")
    public String home() {
        return "Secure Auth API Running 🔥";
    }

    // REGISTER USER
    @GetMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password){

        String hashedPassword = encoder.encode(password);

        User user = new User(username, hashedPassword);

        userRepository.save(user);

        return "User registered successfully 🔥";
    }

    // LOGIN USER
    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){

        Optional<User> foundUser = userRepository.findByUsername(username);

        if(foundUser.isPresent() && encoder.matches(password, foundUser.get().getPassword())){
            return "Login Successful ✅";
        }

        return "Invalid Credentials ❌";
    }
}
