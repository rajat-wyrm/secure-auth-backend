#!/usr/bin/env bash
set -euo pipefail
APP_JWT_SECRET="${APP_JWT_SECRET:-$(openssl rand -hex 32)}" docker compose up -d --build
