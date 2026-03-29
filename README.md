# Backend (Spring Boot + Docker)

## 1. Clone Code

```bash
git clone <your-repo-url>
cd backend
```

After clone, run:

```bash
cp .env.example .env
docker compose up -d --build
```

## 2. Prepare Environment

Create your local environment file:

```bash
cp .env.example .env
```

Default values in `.env` are ready for local Docker usage.

## 3. Start Everything

```bash
docker compose up -d --build
```

This starts:
- Spring Boot app
- MySQL 8
- phpMyAdmin

Note: `docker compose -u --build` is invalid. Use `up -d --build`.

## 4. Access Services

- API: `http://localhost:8000`
- phpMyAdmin: `http://localhost:8081`

phpMyAdmin login:
- Server: `mysql`
- User: `root`
- Password: `root`

## 5. Useful Commands

### Rebuild Docker Image (after pull or code changes)

Run this command in your project root to rebuild the backend Docker image:

```bash
docker compose up -d --build --force-recreate app
```


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
