# Backend (Spring Boot + Docker)

## 1. Prepare Environment

Create your local environment file:

```bash
cp .env.example .env
```

Default values in `.env` are ready for local Docker usage.

## 2. Start Everything

```bash
docker compose up -d --build
```

This starts:
- Spring Boot app
- MySQL 8
- phpMyAdmin

## 3. Access Services

- API: `http://localhost:8000`
- phpMyAdmin: `http://localhost:8081`

phpMyAdmin login:
- Server: `mysql`
- User: `root`
- Password: `root`

## 4. Useful Commands

Show logs:

```bash
docker compose logs -f app
```

Stop stack:

```bash
docker compose down
```

Stop stack and remove DB volume:

```bash
docker compose down -v
```
