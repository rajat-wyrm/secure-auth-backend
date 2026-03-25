package com.project.authapi.dto;

public record AuthResponse(
        String token,
        String userId
) {}
