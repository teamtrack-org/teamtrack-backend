# TeamTrack – Mini Jira Backend

TeamTrack is a mini project management application inspired by Jira.  
This project was developed as part of the **CSE 332 – Software Engineering** course, with a strong focus on **software engineering principles, documentation, and development process**, rather than only coding.

---

## 🚀 Technologies
- **Backend:** Java 21, Spring Boot 3.2.5
- **Database:** PostgreSQL / H2 (for testing)
- **ORM:** JPA / Hibernate
- **Security:** Spring Security, JWT
- **API Documentation:** Swagger/OpenAPI
- **Containerization:** Docker, Docker Compose
- **Version Control:** Git & GitHub
- **Project Management:** GitHub Projects (Mini Jira)
- **Documentation:** SRS, UML (Use Case & Sequence)

---

## 🐳 Quick Start with Docker

The easiest way to run the application is using Docker:

```bash
# Clone the repository
git clone https://github.com/teamtrack-org/teamtrack-backend.git
cd teamtrack-backend

# Start all services (PostgreSQL + Backend + pgAdmin)
docker-compose up --build

# Access the application
# Backend API: http://localhost:8080
# Swagger UI: http://localhost:8080/swagger-ui.html
# pgAdmin: http://localhost:5050
```

For detailed Docker instructions, see [DOCKER.md](DOCKER.md).

---

## 🧠 Project Focus
This project emphasizes:
- Software Development Life Cycle (SDLC)
- Agile / Scrum-inspired workflow
- Clean architecture and layered design
- Proper documentation before implementation
- Test-driven development
- Containerization and deployment

---

## ✅ What Has Been Implemented

### 🔹 Software Engineering Process
- Planned development using **GitHub Projects** with:
  - Backlog
  - To Do
  - In Progress
  - Done
- Simulated an **Agile workflow**, even as an individual developer.

### 🔹 Requirements & Documentation
- Created a **Software Requirements Specification (SRS)** document.
- Designed **UML diagrams**:
  - Use Case Diagrams
  - Sequence Diagrams (Task creation flow)
- Maintained all documentation under `/docs`.

### 🔹 Backend Architecture
- Designed a **layered Spring Boot backend architecture**.
- Implemented core domain entities:
  - `User` (with roles: ADMIN, USER)
  - `Project` (with owner relationship)
  - `Task` (with status lifecycle)
- Defined entity relationships using **JPA annotations** with proper cascade operations.
- Implemented task lifecycle states:
  - `TODO`
  - `IN_PROGRESS`
  - `DONE`

### 🔹 Service Layer
- **ProjectService** with complete CRUD operations
- **TaskService** with status management
- Owner assignment and validation
- Transaction management
- Comprehensive DTO mapping (including owner and tasks)

### 🔹 REST API
- RESTful controllers for Projects and Tasks
- Request/Response DTOs with validation
- Pagination support
- Proper HTTP status codes
- Swagger/OpenAPI documentation

### 🔹 Security
- Spring Security integration
- BCrypt password encoding
- JWT token support (dependencies ready)
- Security configuration for API endpoints
- Health check endpoints (Spring Actuator)

### 🔹 Testing
- Unit tests for Service layer
- Integration tests for Controllers
- MockMvc for controller testing
- Comprehensive test coverage
- All tests passing ✅

### 🔹 Containerization
- Multi-stage Dockerfile for optimized builds
- Docker Compose setup with:
  - PostgreSQL database
  - Spring Boot backend
  - pgAdmin for database management
- Health checks for all services
- Volume persistence for data
- Proper networking between services

### 🔹 Persistence Layer
- Implemented **Spring Data JPA repositories** for all entities.
- Ensured clean separation of concerns using packages:
  - `entity` - Domain models
  - `repository` - Data access layer
  - `service` - Business logic
  - `controller` - REST endpoints
  - `dto` - Data transfer objects
  - `config` - Configuration classes
  - `exception` - Custom exceptions

### 🔹 Version Control & Workflow
- Used **feature branch strategy** (`feature/*`).
- Opened and merged **Pull Requests** for each completed feature.
- Followed professional Git practices with meaningful commit messages.
- Cleaned up merged branches.

---

## 📂 Project Structure

```
teamtrack-backend/
├── src/
│   ├── main/
│   │   ├── java/com/teamtrack/
│   │   │   ├── config/          # Security, etc.
│   │   │   ├── controller/      # REST endpoints
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── entity/          # JPA entities
│   │   │   ├── exception/       # Custom exceptions
│   │   │   ├── repository/      # Data access
│   │   │   └── service/         # Business logic
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application-docker.properties
│   └── test/                    # Unit & Integration tests
├── docs/                        # Documentation
├── Dockerfile                   # Docker image definition
├── docker-compose.yml           # Multi-container setup
├── .dockerignore               # Docker build exclusions
└── DOCKER.md                   # Docker guide

```

---

## 📂 Documentation
- 📄 [Software Requirements Specification (SRS)](docs/SRS.md)
- 📐 [UML Use Case Diagrams](docs/UML-UseCase.md)
- 🔄 [UML Sequence Diagrams](docs/UML-Sequence.md)
- 🧭 [SDLC Documentation](docs/SDLC.md)
- 🐳 [Docker Setup Guide](DOCKER.md)

---

## 🛠️ Development Setup

### Prerequisites
- Java 21
- Maven 3.9+
- PostgreSQL 16 (or use Docker)
- Docker Desktop (optional, for containerized setup)

### Local Development (without Docker)

1. **Clone the repository**
   ```bash
   git clone https://github.com/teamtrack-org/teamtrack-backend.git
   cd teamtrack-backend
   ```

2. **Configure PostgreSQL**
   - Create database: `teamtrack`
   - Update `application.properties` with your credentials

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Run tests**
   ```bash
   mvn test
   ```

### With Docker (Recommended)

See [DOCKER.md](DOCKER.md) for complete Docker setup instructions.

---

## 🔮 Next Steps
- [x] ~~Implement REST controllers (Project & Task APIs)~~
- [x] ~~Add Swagger (OpenAPI) documentation~~
- [x] ~~Add Spring Security~~
- [x] ~~Write unit tests~~
- [x] ~~Add Docker support~~
- [ ] Implement JWT authentication flow
- [ ] Add User registration/login endpoints
- [ ] Implement role-based authorization
- [ ] Add frontend integration
- [ ] Deploy to cloud (AWS/Azure/GCP)

---

## 📊 API Endpoints

### Projects
- `GET /api/projects` - Get all projects (paginated)
- `GET /api/projects/{id}` - Get project by ID
- `POST /api/projects` - Create new project
- `PUT /api/projects/{id}` - Update project
- `DELETE /api/projects/{id}` - Delete project

### Tasks
- `GET /api/tasks` - Get all tasks (paginated)
- `GET /api/tasks/{id}` - Get task by ID
- `POST /api/tasks` - Create new task
- `PUT /api/tasks/{id}` - Update task
- `PATCH /api/tasks/{id}/status` - Update task status
- `DELETE /api/tasks/{id}` - Delete task

### Health Check
- `GET /actuator/health` - Application health status

For detailed API documentation, visit: http://localhost:8080/swagger-ui.html

---

## 👨‍💻 Author
Developed as part of CSE 332 – Software Engineering course.

---

## 📝 License
This project is for educational purposes.
