# Library Management System (Spring Boot + Camunda 8.8)

## Stack
- Java 17, Spring Boot 3.5.x, Maven
- PostgreSQL + Flyway
- Camunda 8.8 (`camunda-spring-boot-starter`) using REST-first integration
- JWT security with roles: `ROLE_LIBRARIAN`, `ROLE_MEMBER`

## Profiles
- `dev` (default), `prod`, `saas`

## Camunda configuration
Set env vars:
- `CAMUNDA_BASE_URL`
- `CAMUNDA_AUTH_STRATEGY` (BASIC/OAUTH)
- `CAMUNDA_CLIENT_ID`
- `CAMUNDA_CLIENT_SECRET`
- `CAMUNDA_OAUTH_URL`
- `CAMUNDA_OAUTH_AUDIENCE`

## Email connector options
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
- `POST /api/v1/loans/borrow`
- `POST /api/v1/loans/return`
- `POST /api/v1/loans/extend`

### Librarian/admin APIs
- `GET /api/v1/admin/tasks`
- `POST /api/v1/admin/tasks/{taskId}/claim`
- `POST /api/v1/admin/tasks/{taskId}/unassign`
- `POST /api/v1/admin/tasks/{taskId}/complete`
- `POST /api/v1/admin/books`
- `GET /api/v1/admin/loans/overdue`
- `POST /api/v1/admin/processes/deploy`
- `GET /api/v1/admin/processes/{requestId}`

### Internal process APIs
- `POST /api/v1/internal/processes/borrow/{requestId}/reserve`
- `POST /api/v1/internal/processes/return/{requestId}/settle`

## Sample curl
```bash
curl -X POST http://localhost:8080/api/v1/auth/login -H 'Content-Type: application/json' -d '{"username":"librarian@test","password":"password"}'
curl -X POST http://localhost:8080/api/v1/loans/borrow -H 'Authorization: Bearer <token>' -H 'Content-Type: application/json' -d '{"memberId":1,"bookId":1}'
curl http://localhost:8080/api/v1/admin/tasks -H 'Authorization: Bearer <librarian-token>'
```

## Run
```bash
docker compose up -d postgres
./mvnw spring-boot:run
```

## BPMN models
- `BorrowBook`
- `ReturnBook`
- `DueDateReminder`
- `OnboardMember`

Located under `src/main/resources/processes`.
