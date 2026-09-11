# Project Name : Journal Application API

A secure, production-grade RESTful backend web service built using **Java** and the **Spring Boot Ecosystem**. This application features a structural layered architecture, strict environment isolation, and enterprise-grade security protocols.

## 🛡️ Core Security Architecture (Spring Security)
The application is fortified using **Spring Security** to handle Authentication, Authorization, and secure data access:
* **Authentication Method:** Implemented [Basic Auth / JWT (JSON Web Tokens)] to handle user credentials securely.
* **Role-Based Access Control (RBAC):** Configured user permissions using `@PreAuthorize` or `SecurityFilterChain` matchers to protect sensitive administrative endpoints.
* **Password Encryption:** Secured user credentials in the database using the **BCryptPasswordEncoder** hashing algorithm.

## 🌟 Key Application Features
* **Layered Architecture:** Follows a strict Controller-Service-Repository pattern for complete separation of concerns.
* **Global Error Management:** Centralized exception handling via `@ControllerAdvice` providing clean, uniform HTTP error payloads.
* **Environment Isolation (Profiles):** Decoupled application properties into `dev` and `prod` files to isolate database nodes and encrypt system keys.

## 🛠️ Tech Stack & Ecosystem
* **Core Language:** Java 17 (or [Your Version])
* **Framework:** Spring Boot v3.x (or [Your Version])
* **Security Framework:** Spring Security
* **Data Access:** [Spring Data MongoDB / Spring Data JPA]
* **Database:** [MongoDB Atlas / Local MongoDB / MySQL]
* **Build Tool:** Maven

## 📁 System Architecture & Directory Layout
```text
src/main/java/com/[yourcompany]/[projectname]/
│
├── controller/  # Secure REST API Endpoints & Request Routing
├── service/     # Business Logic Processing & Password Hashing
├── repository/  # Database Query Layer & Aggregations
├── model/       # Core Data Blueprints (User, Journal, Roles Entities)
└── config/      # System Security Config & Profile Management Beans
```

## ⚙️ Environment Configuration (Spring Profiles)
The project utilizes distinct runtime profiles to manage configurations safely across environments:

1. **Development (`Dev`):** Connects to a local sandbox instance. Features verbose database logging and a testing security posture.
2. **Production (`Prod`):** Streamlined for security. Database URLs, JWT secrets, and production keys are injected via environment variables to avoid credential leakage on GitHub.

### How to Run the Secure App Locally

**For Dev Environment:**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=Dev
```

**For Prod Environment:**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=Prod
```

---
*Note: This repository represents a fully secured backend architecture mapped against production workflows.*
