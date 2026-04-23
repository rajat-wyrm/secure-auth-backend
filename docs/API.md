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
