# AuthController: POST /register, POST /login, returns JWT
# AuthController: POST /register, POST /login, returns JWT
# AuthController: POST /register, POST /login, returns JWT
package com.project.authapi;

import com.project.authapi.dto.AuthResponse;
import com.project.authapi.dto.LoginRequest;
import com.project.authapi.dto.RegisterRequest;
import com.project.authapi.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
