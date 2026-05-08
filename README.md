# Secure Auth Backend

Production-grade authentication API. Spring Boot + MongoDB + JWT + Spring Security.

## Features

- **POST-based auth** — credentials never go in URLs / access logs.
- **BCrypt password hashing** with cost factor 12.
- **JWT issuance** (HS256, configurable expiry, secret via env var).
- **Stateless session** — Spring Security configured with `SessionCreationPolicy.STATELESS`.
- **Input validation** via Jakarta Bean Validation.
- **Centralized error handling** via `@RestControllerAdvice`.
- **MongoDB unique index** on `username` (race-free duplicate prevention).
- **Health check** at `/actuator/health`.
- **Tests:** controller integration + repository + JWT round-trip.

## Endpoints

| Method | Path                  | Auth   | Body                                | Returns                  |
|--------|-----------------------|--------|-------------------------------------|--------------------------|
| GET    | `/api/auth/`          | none   | —                                   | `{ "Secure Auth API..." }` |
| POST   | `/api/auth/register`  | none   | `{ username, password }`            | `201 { token, userId }`  |
| POST   | `/api/auth/login`     | none   | `{ username, password }`            | `200 { token, userId }`  |
| GET    | `/api/auth/me`        | Bearer | —                                   | `200 { userId }`         |
| GET    | `/actuator/health`    | none   | —                                   | `200 "UP"`               |

## Quick start

```bash
export APP_JWT_SECRET="$(openssl rand -hex 32)"
export MONGODB_URI="mongodb://localhost:27017/authdb"
mvn spring-boot:run
```

### Register

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H 'Content-Type: application/json' \
  -d '{"username":"alice","password":"Sup3rSecret!"}'
```

### Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H 'Content-Type: application/json' \
  -d '{"username":"alice","password":"Sup3rSecret!"}'
```

### Use the token

```bash
TOKEN="<paste-token-here>"
curl http://localhost:8080/api/auth/me -H "Authorization: Bearer $TOKEN"
```

## Configuration

| Env var                    | Default                                       | Notes                            |
|----------------------------|-----------------------------------------------|----------------------------------|
| `PORT`                     | `8080`                                        | server port                      |
| `MONGODB_URI`              | `mongodb://localhost:27017/authdb`            | full Mongo connection string     |
| `APP_JWT_SECRET`           | _(required, >= 32 chars)_                      | HS256 signing key                 |
| `APP_JWT_EXPIRATION_HOURS` | `24` (`12` in prod profile)                    | token lifetime                   |

Spring profiles:
- default — dev
- `prod` — stricter logging, no health details
- `test` — `application-test.properties` for integration tests

## Tests

```bash
mvn test
```

The test profile uses an in-process MongoDB via `de.flapdoodle.embed.mongo.spring3x` so no external service is required.

## Security model

- Passwords are never logged or returned in responses.
- JWT is signed with HS256; tampering invalidates the signature.
- MongoDB unique index prevents duplicate usernames at the storage layer.
- Global exception handler returns sanitized error responses — no stack traces leak to clients.
- All `/api/auth/*` endpoints except `/`, `/register`, `/login`, and `/actuator/health` require a valid Bearer token.

## License

MIT
