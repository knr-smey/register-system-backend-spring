# 🚀 Backend Setup

## 📥 1. Clone Project

```bash
git clone <your-repo-url>
cd backend
```

---

## ⚙️ 2. Build Project

```bash
./mvnw clean package -DskipTests
```

---

## 🐳 3. Run Docker

```bash
docker-compose up -d --build
```

---

## 🌐 4. Access

* API → http://localhost:8000
* phpMyAdmin → http://localhost:8081

Login phpMyAdmin:

```
Server: mysql
User: root
Password: root
```

---

## 🛑 Stop Project

```bash
docker-compose down
```
