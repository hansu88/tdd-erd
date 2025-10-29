# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**hhplus-erd-jvm** is a Spring Boot 3.5.7 application using Java 17. This is an early-stage project scaffold prepared for ERD (Entity Relationship Diagram) implementation work, likely as part of the Hanghae Plus education program.

- **Group**: com.h2
- **Package**: com.h2.hhpluserdjvm
- **Build Tool**: Gradle 8.14.3 with wrapper
- **Main Branch**: main
- **Current Development Branch**: step-1-erd

## Common Commands

### Building and Running
```bash
# Build the project
./gradlew clean build

# Run the application
./gradlew bootRun

# Build without tests
./gradlew build -x test
```

### Testing
```bash
# Run all tests
./gradlew test

# Run a single test class
./gradlew test --tests HhplusErdJvmApplicationTests

# Run a specific test method
./gradlew test --tests HhplusErdJvmApplicationTests.contextLoads
```

### Gradle Tasks
```bash
# List all available tasks
./gradlew tasks

# View dependencies
./gradlew dependencies
```

## Architecture and Structure

### Current State
The project is in its initial scaffold stage with minimal implementation:
- Single main application class: `HhplusErdJvmApplication.java`
- Basic Spring Boot auto-configuration setup
- No controllers, services, or repositories yet
- No database configuration

### Expected Layer Structure
Based on Spring Boot best practices, future development should follow this layered architecture:

```
src/main/java/com/h2/hhpluserdjvm/
├── controller/     # REST API endpoints
├── service/        # Business logic layer
├── repository/     # Data access layer
├── domain/         # Entity models and domain objects
├── dto/            # Data Transfer Objects
└── config/         # Configuration classes
```

### Dependencies
- **Spring Boot Starter Web**: For REST API development
- **Lombok**: Reduces boilerplate code (getters, setters, constructors)
  - Use `@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor` annotations
- **Spring Boot Starter Test**: Includes JUnit 5, Mockito, AssertJ

### Configuration
- Application configuration: `src/main/resources/application.properties`
- Currently only sets `spring.application.name=hhplus-erd-jvm`
- No database, security, or other services configured yet

## Development Notes

### Testing Approach
- Using JUnit 5 (Jupiter) for all tests
- Integration tests use `@SpringBootTest` annotation
- Test files mirror the main source structure in `src/test/java`

### Branch Strategy
- Development appears to follow step-based branching (e.g., `step-1-erd`)
- Merge to `main` branch for stable releases

### Missing Components
When adding features, you'll likely need to:
1. Add database dependencies (e.g., Spring Data JPA, H2/MySQL/PostgreSQL driver)
2. Create entity models and repositories
3. Add API controllers and service layer
4. Configure database connection in application.properties
5. Add API documentation (Swagger/SpringDoc) if building REST APIs
