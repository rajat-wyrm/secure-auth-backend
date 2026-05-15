# Changelog

All notable changes to secure-auth-backend will be documented here.

## [0.0.1-SNAPSHOT] - 2026-03-03

- initial Spring Boot scaffold
- MongoDB connected
- broken /register and /login on GET

## [0.0.2] - 2026-03-08

- add Spring Security + JJWT deps (not yet wired)
- fix pom.xml typo

## [0.1.0] - 2026-03-15

- switch /register and /login to POST
- add input validation

## [0.1.1] - 2026-03-19

- fix: BCrypt cost 10 -> 12
- fix: duplicate username returns 409

## [0.2.0] - 2026-03-25

- add DTOs (RegisterRequest, LoginRequest, AuthResponse)
- add ErrorResponse DTO

## [0.2.1] - 2026-03-29

- fix: @Indexed(unique=true) on username
- add Mongo index

## [0.3.0] - 2026-04-04

- add JWT issuance (HS256)
- JwtService bean

## [0.3.1] - 2026-04-08

- fix: enforce 32-char secret at startup
- fail fast on weak config

## [0.4.0] - 2026-04-15

- add Spring Security stateless config
- BCrypt bean via PasswordEncoder

## [0.4.1] - 2026-04-19

- add JwtAuthFilter
- Bearer token extraction

## [0.4.2] - 2026-04-22

- fix: clear context on invalid token
- test: jwt roundtrip

## [0.5.0] - 2026-04-28

- add GlobalExceptionHandler
- sanitized error responses

## [0.5.1] - 2026-05-02

- add application.properties + dev/prod/test profiles
- fix: typo in description

