<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> main
# Library Management System (REST Architecture)

This project is implemented as a **REST API backend** (no server-side MVC views), with layered packages:
- `controller` (REST resources)
- `service` (business logic / orchestration)
- `repository` (Spring Data JPA)
- `entity` / `dto`
<<<<<<< HEAD
=======
=======
# Library Management System (Spring Boot + Camunda 8.8)
>>>>>>> main
>>>>>>> main

## Stack
- Java 17, Spring Boot 3.5.x, Maven
- PostgreSQL + Flyway
<<<<<<< HEAD
- Camunda 8.8 (`camunda-spring-boot-starter`) with REST-first integration
- JWT security (`ROLE_LIBRARIAN`, `ROLE_MEMBER`)
=======
<<<<<<< HEAD
- Camunda 8.8 (`camunda-spring-boot-starter`) with REST-first integration
- JWT security (`ROLE_LIBRARIAN`, `ROLE_MEMBER`)
=======
- Camunda 8.8 (`camunda-spring-boot-starter`) using REST-first integration
- JWT security with roles: `ROLE_LIBRARIAN`, `ROLE_MEMBER`
>>>>>>> main
>>>>>>> main

## Profiles
- `dev` (default), `prod`, `saas`

<<<<<<< HEAD
## Camunda configuration (env)
=======
<<<<<<< HEAD
## Camunda configuration (env)
=======
## Camunda configuration
Set env vars:
>>>>>>> main
>>>>>>> main
- `CAMUNDA_BASE_URL`
- `CAMUNDA_AUTH_STRATEGY` (BASIC/OAUTH)
- `CAMUNDA_CLIENT_ID`
- `CAMUNDA_CLIENT_SECRET`
- `CAMUNDA_OAUTH_URL`
- `CAMUNDA_OAUTH_AUDIENCE`

## Email connector options
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> main
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
<<<<<<< HEAD
=======
=======
1. SMTP Email Connector (host/port/username/password/TLS)
2. SendGrid Connector (API key secret)

Templates in `src/main/resources/templates`.

## Endpoints
### Auth
- `POST /api/v1/auth/login`

### Public member APIs
- `POST /api/v1/members`
- `GET /api/v1/members/{memberId}`
- `GET /api/v1/members/{memberId}/loans`
- `GET /api/v1/books?author=`
>>>>>>> main
>>>>>>> main
- `POST /api/v1/loans/borrow`
- `POST /api/v1/loans/return`
- `POST /api/v1/loans/extend`

<<<<<<< HEAD
### Librarian/Admin APIs
=======
<<<<<<< HEAD
### Librarian/Admin APIs
=======
### Librarian/admin APIs
>>>>>>> main
>>>>>>> main
- `GET /api/v1/admin/tasks`
- `POST /api/v1/admin/tasks/{taskId}/claim`
- `POST /api/v1/admin/tasks/{taskId}/unassign`
- `POST /api/v1/admin/tasks/{taskId}/complete`
- `POST /api/v1/admin/books`
<<<<<<< HEAD
- `PUT /api/v1/admin/books/{id}`
- `PATCH /api/v1/admin/books/{id}`
- `DELETE /api/v1/admin/books/{id}`
=======
<<<<<<< HEAD
- `PUT /api/v1/admin/books/{id}`
- `PATCH /api/v1/admin/books/{id}`
- `DELETE /api/v1/admin/books/{id}`
=======
>>>>>>> main
>>>>>>> main
- `GET /api/v1/admin/loans/overdue`
- `POST /api/v1/admin/processes/deploy`
- `GET /api/v1/admin/processes/{requestId}`

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> main
### Internal Process APIs
- `POST /api/v1/internal/processes/borrow/{requestId}/reserve`
- `POST /api/v1/internal/processes/return/{requestId}/settle`

## Tests
- Unit: `RequestIdServiceTest`
- Controller slice tests: `AuthControllerTest`, `BookControllerTest`, `LoanControllerTest`
- Integration skeleton: `LmsIntegrationTest` (Testcontainers)
<<<<<<< HEAD
=======
=======
### Internal process APIs
- `POST /api/v1/internal/processes/borrow/{requestId}/reserve`
- `POST /api/v1/internal/processes/return/{requestId}/settle`

## Sample curl
```bash
curl -X POST http://localhost:8080/api/v1/auth/login -H 'Content-Type: application/json' -d '{"username":"librarian@test","password":"password"}'
curl -X POST http://localhost:8080/api/v1/loans/borrow -H 'Authorization: Bearer <token>' -H 'Content-Type: application/json' -d '{"memberId":1,"bookId":1}'
curl http://localhost:8080/api/v1/admin/tasks -H 'Authorization: Bearer <librarian-token>'
```
>>>>>>> main
>>>>>>> main

## Run
```bash
docker compose up -d postgres
./mvnw spring-boot:run
```
<<<<<<< HEAD

## Troubleshooting startup
If you see `password authentication failed` during Flyway initialization, your active profile is using wrong DB credentials.

Use values that match your running Postgres:
```bash
export DB_URL=jdbc:postgresql://localhost:5432/lms
export DB_USERNAME=lms
export DB_PASSWORD=lms
```
(or set them in IDE Run Configuration).

`application-dev.yml` now defaults to the same credentials used by `docker-compose.yml`.
=======
<<<<<<< HEAD
=======

## BPMN models
- `BorrowBook`
- `ReturnBook`
- `DueDateReminder`
- `OnboardMember`

Located under `src/main/resources/processes`.
>>>>>>> main
>>>>>>> main
