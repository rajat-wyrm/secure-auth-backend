# JwtService: HS256 token gen + parse, 32-char secret enforced
# JwtService: HS256 token gen + parse, 32-char secret enforced
# JwtService: HS256 token gen + parse, 32-char secret enforced
package com.project.authapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
