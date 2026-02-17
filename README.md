# Library Management System (REST Architecture)

This project is implemented as a **REST API backend** (no server-side MVC views), with layered packages:
- `controller` (REST resources)
- `service` (business logic / orchestration)
- `repository` (Spring Data JPA)
- `entity` / `dto`

## Stack
- Java 17, Spring Boot 3.5.x, Maven
- PostgreSQL + Flyway
- Camunda 8.8 (`camunda-spring-boot-starter`) with REST-first integration
- JWT security (`ROLE_LIBRARIAN`, `ROLE_MEMBER`)

## Profiles
- `dev` (default), `prod`, `saas`

## Camunda configuration (env)
- `CAMUNDA_BASE_URL`
- `CAMUNDA_AUTH_STRATEGY` (BASIC/OAUTH)
- `CAMUNDA_CLIENT_ID`
- `CAMUNDA_CLIENT_SECRET`
- `CAMUNDA_OAUTH_URL`
- `CAMUNDA_OAUTH_AUDIENCE`

## Email connector options
1. SMTP Email Connector
2. SendGrid Connector

Templates are under `src/main/resources/templates`.

## REST Endpoints (`/api/v1`)
### Auth
- `POST /api/v1/auth/login`

### Member APIs
- `POST /api/v1/members`
- `GET /api/v1/members/{memberId}`
- `GET /api/v1/members/{memberId}/loans`
- `GET /api/v1/books?author=&categoryId=&availableOnly=`
- `POST /api/v1/loans/borrow`
- `POST /api/v1/loans/return`
- `POST /api/v1/loans/extend`

### Librarian/Admin APIs
- `GET /api/v1/admin/tasks`
- `POST /api/v1/admin/tasks/{taskId}/claim`
- `POST /api/v1/admin/tasks/{taskId}/unassign`
- `POST /api/v1/admin/tasks/{taskId}/complete`
- `POST /api/v1/admin/books`
- `PUT /api/v1/admin/books/{id}`
- `PATCH /api/v1/admin/books/{id}`
- `DELETE /api/v1/admin/books/{id}`
- `GET /api/v1/admin/loans/overdue`
- `POST /api/v1/admin/processes/deploy`
- `GET /api/v1/admin/processes/{requestId}`

### Internal Process APIs
- `POST /api/v1/internal/processes/borrow/{requestId}/reserve`
- `POST /api/v1/internal/processes/return/{requestId}/settle`

## Tests
- Unit: `RequestIdServiceTest`
- Controller slice tests: `AuthControllerTest`, `BookControllerTest`, `LoanControllerTest`
- Integration skeleton: `LmsIntegrationTest` (Testcontainers)

## Run
```bash
docker compose up -d postgres
./mvnw spring-boot:run
```
