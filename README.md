# SpringCRM

A backend CRM application built with **Java 17** and **Spring Boot 3.3**. The project demonstrates REST API development, JWT authentication, role-based authorization, PostgreSQL persistence, DTO mapping, Liquibase migrations, OpenAPI documentation, Docker-based local infrastructure, and automated context testing.

## Features

- User registration and authentication
- BCrypt password hashing
- JWT-based stateless authentication
- Role-based authorization with Spring Security
- User role management
- Course CRUD operations
- Teacher and student relationships
- Student enrollment in courses
- Request validation with Jakarta Bean Validation
- DTO-based API layer with MapStruct
- PostgreSQL persistence with Spring Data JPA / Hibernate
- Liquibase database migrations
- OpenAPI / Swagger documentation
- Docker Compose PostgreSQL environment
- Isolated H2 application-context test profile

## Tech Stack

| Category | Technologies |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.3 |
| Web | Spring Web, REST API |
| Security | Spring Security, JWT (JJWT), BCrypt |
| Persistence | Spring Data JPA, Hibernate |
| Database | PostgreSQL |
| Migrations | Liquibase |
| Mapping | MapStruct |
| Validation | Jakarta Bean Validation |
| Documentation | OpenAPI / Swagger |
| Build | Maven |
| Containerization | Docker, Docker Compose |
| Testing | JUnit 5, Spring Boot Test, H2 |
| Utilities | Lombok |

## Architecture

The application uses a layered architecture:

```text
HTTP Request
     |
     v
Controller
     |
     v
Service
     |
     v
Repository
     |
     v
PostgreSQL
```

Cross-cutting concerns are handled by dedicated components:

```text
JWT Authentication -> Spring Security Filter Chain
DTO Mapping        -> MapStruct
Validation         -> Jakarta Bean Validation
Exceptions         -> GlobalExceptionHandler
Schema Changes     -> Liquibase
```

## API Overview

### Authentication

```text
POST /api/users/auth/signup
POST /api/users/auth/signin
```

### Administration

```text
GET /api/users/admin-test
PUT /api/users/roles?email={email}&role={role}
```

### Courses

```text
GET    /api/courses
GET    /api/courses/{id}
POST   /api/courses?teacherId={teacherId}
DELETE /api/courses/{id}
POST   /api/courses/{courseId}/students/{studentId}
GET    /api/courses/{courseId}/students
GET    /api/courses/teacher/{teacherId}
```

Protected operations require the appropriate role (`ROLE_ADMIN`, `ROLE_MANAGER`, or `ROLE_TEACHER`).

## Project Structure

```text
src/main/java/practice/springcrm/
├── config/        # Security and OpenAPI configuration
├── controller/    # REST controllers
├── dto/           # Request and response DTOs
├── entity/        # JPA entities and roles
├── exception/     # Global REST exception handling
├── mapper/        # MapStruct mappers
├── repository/    # Spring Data repositories
├── security/      # JWT provider and authentication filter
└── service/       # Business logic
```

## Getting Started

### Prerequisites

- JDK 17+
- Docker and Docker Compose
- Maven 3.9+ (or use the included Maven Wrapper)

### 1. Clone

```bash
git clone https://github.com/devtilek/CRM-system.git
cd CRM-system
```

### 2. Start PostgreSQL

```bash
docker compose up -d db
```

### 3. Configure environment variables

Copy `.env.example` as a reference, then configure the following environment variables in your shell or IDE:

```text
DB_URL=jdbc:postgresql://localhost:5432/SpringCRM
DB_USERNAME=postgres
DB_PASSWORD=postgres
JWT_SECRET_ACCESS=base64-encoded-secret-with-at-least-256-bits
```

The JWT secret must decode to at least 256 bits because the application uses an HMAC signing key.

### 4. Run the application

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

### 5. Open API documentation

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI JSON:

```text
http://localhost:8080/v3/api-docs
```

## Testing

The test suite uses an isolated in-memory H2 database, so tests do not require a running PostgreSQL instance.

```bash
./mvnw test
```

## Docker

The Dockerfile packages the application into a lightweight Eclipse Temurin Java 17 runtime image. Docker Compose is provided for the local PostgreSQL database.

## Environment & Security

- Secrets are provided through environment variables.
- Local `.env` files are ignored by Git.
- Passwords are stored using BCrypt hashes.
- JWT authentication is stateless.
- Database schema changes are managed by Liquibase.
- IDE metadata is excluded from version control.

## Project Status

Educational portfolio backend project focused on demonstrating clean Spring Boot backend development patterns.

## Author

**Aktilek Korganbek**

- GitHub: https://github.com/devtilek
- Focus: Java Backend Development
