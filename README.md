# SpringCRM

A backend CRM application built with **Java 17** and **Spring Boot 3.3**. The project demonstrates REST API development, JWT authentication, role-based authorization, persistence with PostgreSQL, DTO mapping, database migrations, API documentation, and Docker-based local infrastructure.

## Features

- User registration and authentication
- JWT-based authentication
- Role-based authorization with Spring Security
- User and course management
- Course-to-student relationships
- Request validation with Jakarta Bean Validation
- DTO-based API layer with MapStruct
- PostgreSQL persistence with Spring Data JPA / Hibernate
- Liquibase database migrations
- OpenAPI / Swagger documentation
- Docker Compose configuration for PostgreSQL
- Basic Spring Boot integration test setup

## Tech Stack

| Category | Technologies |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.3 |
| Web | Spring Web, REST API |
| Security | Spring Security, JWT (JJWT) |
| Persistence | Spring Data JPA, Hibernate |
| Database | PostgreSQL |
| Migrations | Liquibase |
| Mapping | MapStruct |
| Validation | Jakarta Bean Validation |
| Documentation | OpenAPI / Swagger |
| Build | Maven |
| Containerization | Docker, Docker Compose |
| Testing | JUnit 5, Spring Boot Test |
| Utilities | Lombok |

## Architecture

The application follows a layered backend structure:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

DTOs and MapStruct are used between the API and service layers, while Spring Security handles authentication and authorization.

## API Overview

### Authentication

```text
POST /api/users/auth/signup
POST /api/users/auth/signin
```

### Users / Administration

```text
GET  /api/users/admin-test
POST /api/users/admin/change-role
```

### Courses

```text
GET    /api/courses
GET    /api/courses/{id}
POST   /api/courses/addCourse
DELETE /api/courses/{id}
GET    /api/courses/{courseId}/students
```

Some endpoints require specific roles such as `ROLE_ADMIN` or `ROLE_MANAGER`.

## Project Structure

```text
src/main/java/practice/springcrm/
├── config/        # OpenAPI and security configuration
├── controller/    # REST controllers
├── dto/           # Request and response DTOs
├── entity/        # JPA entities and roles
├── mapper/        # MapStruct mappers
├── repository/    # Spring Data repositories
├── service/       # Business logic
└── security/      # JWT-related security components
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

Create environment variables for the application database and JWT secret. You can use the provided `application-example.properties` as a starting point.

```text
DB_URL=jdbc:postgresql://localhost:5432/SpringCRM
DB_USERNAME=postgres
DB_PASSWORD=postgres
JWT_SECRET=change-me-to-a-long-random-secret
```

### 4. Run the application

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

### 5. Open API documentation

After the application starts, open:

```text
http://localhost:8080/swagger-ui/index.html
```

OpenAPI JSON is available at:

```text
http://localhost:8080/v3/api-docs
```

## Testing

Run the test suite with:

```bash
./mvnw test
```

## Docker

The repository contains a Dockerfile for running the packaged Spring Boot application and Docker Compose configuration for the PostgreSQL database.

## Project Status

This project is an educational backend application and is intended to demonstrate Spring Boot backend development patterns and technologies.

## Author

**Aktilek Korganbek**

- GitHub: https://github.com/devtilek
- Focus: Java Backend Development
