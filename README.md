# SpringCRM

SpringCRM is a robust Customer Relationship Management (CRM) prototype
built with Spring Boot. It provides a foundational structure for managing
users and their associated courses, featuring secure JWT-based
authentication and a clean layered architecture.

## 🚀 Features

- **User Management**: Registration and authentication system.
- **Course Management**: CRUD operations for courses with User-Course
  relationships.
- **Security**: JWT (JSON Web Token) authentication to protect API
  endpoints.
- **Documentation**: Integrated OpenAPI (Swagger) for easy API testing.
- **Clean Architecture**: Follows the Service-Repository-Controller
  pattern with DTOs and MapStruct for object mapping.

## 🛠 Tech Stack

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA** (Hibernate)
- **Spring Security** (JWT)
- **MapStruct** (Mapping)
- **Lombok** (Boilerplate reduction)
- **H2 / PostgreSQL** (Database supported)

## 📋 Getting Started

### Prerequisites
- JDK 17 or higher
- Maven 3.x

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/YOUR_USERNAME/SpringCRM.git

2. Navigate to the project directory:
   cd SpringCRM
3. Run the application:
   ./mvnw spring-boot:run
4. Access the Swagger UI to explore the API:
   http://localhost:8080/swagger-ui.html

🏗 Project Structure

- controller/: REST endpoints.
- service/: Business logic.
- repository/: Database access.
- entity/: Database models.
- dto/: Data Transfer Objects.
- security/: JWT and Security configuration.

🤝 Contributing

Contributions are welcome! If you have any suggestions to improve the CRM
functionality or code structure, feel free to open a Pull Request.

📄 License

This project is open-source and available for educational purposes.