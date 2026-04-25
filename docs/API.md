# API Reference

## POST `/api/auth/register`

Request:
```json
{ "username": "alice", "password": "Sup3rSecret!" }
```

Response 201:
```json
{ "token": "<jwt>", "userId": "65f0a..." }
```

Response 400 (validation):
```json
{
  "status": 400,
  "error": "Validation failed",
  "message": "Request body has invalid fields",
  "timestamp": "2026-05-30T12:00:00Z",
  "details": { "password": "size must be between 8 and 128" }
}
```

Response 409 (duplicate username):
```json
{ "status": 409, "error": "Conflict", "message": "Resource already exists", "timestamp": "...", "details": {} }
```

## POST `/api/auth/login`

Request: same as register.

Response 200: `{ token, userId }`
Response 401: `{ status: 401, error: "Unauthorized", ... }`

## GET `/api/auth/me`

Requires `Authorization: Bearer <token>`.

Response 200:
```json
{ "userId": "65f0a..." }
```

Response 401: missing or invalid token.
