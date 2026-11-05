# contact-app

[![Java CI with Maven](https://github.com/hendisantika/contact-app/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/contact-app/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1-6DB33F?logo=springboot)
![React](https://img.shields.io/badge/React-19-61DAFB?logo=react)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?logo=postgresql)

A full-stack contact management application: a **Spring Boot** REST API backed by **PostgreSQL**, and a **React** frontend
for browsing contacts, viewing details, and uploading profile photos.

## Tech Stack

| Layer    | Technology                                                      |
|----------|-----------------------------------------------------------------|
| Language | Java 25                                                         |
| Backend  | Spring Boot 4.1 (Web, Data JPA, Docker Compose support), Lombok |
| Database | PostgreSQL 16 (via Docker Compose)                              |
| Testing  | JUnit 5, Spring Boot Test, Testcontainers                       |
| Frontend | React 19, React Router, Axios, React Toastify                   |
| Build    | Maven (wrapper included), npm                                   |
| CI       | GitHub Actions, Dependabot                                      |

## Project Structure

```
contact-app/
├── src/main/java/id/my/hendisantika/contactapp
│   ├── config/        # CORS config & startup data seeder (50 fake contacts)
│   ├── controller/    # REST endpoints (/contacts)
│   ├── entity/        # Contact JPA entity, Status enum, constants
│   ├── repository/    # Spring Data JPA repository
│   └── service/       # Business logic & photo storage
├── src/test/java/...  # Integration tests using Testcontainers PostgreSQL
├── frontend/          # React application
├── compose.yaml       # PostgreSQL service for local development
└── .github/           # CI workflow & Dependabot config
```

## Prerequisites

- **JDK 25**
- **Docker** (for PostgreSQL via Docker Compose and for Testcontainers in tests)
- **Node.js** 18+ and npm (for the frontend)

## Getting Started

### 1. Run the backend

```bash
git clone https://github.com/hendisantika/contact-app.git
cd contact-app
./mvnw spring-boot:run
```

Thanks to `spring-boot-docker-compose`, the PostgreSQL container defined in `compose.yaml` is started automatically and
the datasource is wired to it. On startup, 50 random contacts are seeded using JavaFaker.

The API is available at <http://localhost:8080/contacts>.

> Prefer to manage the database yourself? Run `docker compose up -d` and point `spring.datasource.*` in
> `src/main/resources/application.yml` to your PostgreSQL instance (default: `localhost:5433/contactDB`).

### 2. Run the frontend

```bash
cd frontend
npm install
npm start
```

The UI opens at <http://localhost:3000> and talks to the backend at `http://localhost:8080/contacts`
(CORS allows `localhost:3000` and `localhost:4200`).

## REST API

| Method | Endpoint                     | Description                                          |
|--------|------------------------------|------------------------------------------------------|
| `GET`  | `/contacts?page=0&size=10`   | List contacts (paginated, sorted by name)            |
| `GET`  | `/contacts/{id}`             | Get a contact by id                                  |
| `POST` | `/contacts`                  | Create or update a contact                           |
| `PUT`  | `/contacts/photo`            | Upload a photo (`multipart/form-data`: `id`, `file`) |
| `GET`  | `/contacts/image/{filename}` | Fetch an uploaded photo (PNG/JPEG)                   |

Example:

```bash
curl -X POST http://localhost:8080/contacts \
  -H 'Content-Type: application/json' \
  -d '{"name":"Uzumaki Naruto","email":"naruto@konoha.id","title":"Hokage","phone":"08123456789","address":"Konoha","status":"ACTIVE"}'
```

Contact `status` is one of `ACTIVE`, `INACTIVE`, `SUSPENDED`. Uploaded photos are stored in `~/Downloads/uploads/`.

## Testing

```bash
./mvnw test
```

Tests use [Testcontainers](https://testcontainers.com/) with `@ServiceConnection` to start a throwaway
`postgres:16-alpine` container, so no local database is required — only a running Docker daemon.

## Build

```bash
./mvnw clean package            # builds target/contact-app-0.0.1-SNAPSHOT.jar
./mvnw spring-boot:build-image  # builds an OCI image with Cloud Native Buildpacks
```

## Continuous Integration

- **GitHub Actions** (`.github/workflows/maven.yml`) builds and tests the project on JDK 25 (Temurin) for every push and
  pull request to `main`, then submits the Maven dependency graph.
- **Dependabot** (`.github/dependabot.yml`) keeps dependencies up to date:

  | Ecosystem      | Directory   | Schedule        | Groups                                               |
  |----------------|-------------|-----------------|------------------------------------------------------|
  | Maven          | `/`         | daily 05:00 WIB | `spring-boot`, `testcontainers`, `maven-minor-patch` |
  | npm            | `/frontend` | daily 05:00 WIB | `react`, `testing-library`, `npm-minor-patch`        |
  | GitHub Actions | `/`         | weekly (Monday) | `github-actions`                                     |
  | Docker Compose | `/`         | weekly (Monday) | —                                                    |

  Pull requests use conventional commit prefixes (`chore(deps)`, `chore(deps-dev)`, `ci(deps)`), are labelled, and
  wait a 3-day cooldown after a release before being opened.

## Author

**Hendi Santika** — [s.id/hendisantika](https://s.id/hendisantika) · Telegram [@hendisantika34](https://t.me/hendisantika34)
