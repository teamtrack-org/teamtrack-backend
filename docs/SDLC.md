# Software Development Life Cycle (SDLC)

## 1. Requirements Analysis
The development process started with requirements analysis.  
Functional and non-functional requirements were identified and documented in the **Software Requirements Specification (SRS)**.

Key functional requirements:
- User management
- Project creation
- Task management with lifecycle states

Key non-functional requirements:
- Maintainability
- Clean architecture
- Version control discipline

---

## 2. System Design
Before implementation, the system design was planned using UML diagrams:
- **Use Case Diagrams** to identify actors and system interactions
- **Sequence Diagrams** to model key workflows such as task creation

The backend was designed using a **layered architecture**:
- Entity layer
- Repository layer
- Controller layer

---

## 3. Implementation
The implementation phase followed the design and documentation strictly:
- Spring Boot was used for backend development.
- Core entities (`User`, `Project`, `Task`) were implemented using JPA.
- Relationships between entities were defined using ORM annotations.
- Repository interfaces were created using Spring Data JPA.

Each feature was developed in a **separate Git feature branch**.

---

## 4. Testing (Planned)
Formal unit testing is planned for later stages:
- Repository tests
- Controller tests
- API validation tests

---

## 5. Deployment (Planned)
Deployment is planned using:
- Docker (for containerization)
- PostgreSQL as the production database

---

## 6. Version Control Strategy
The project follows professional Git practices:
- `main` branch remains stable
- Feature development is done in `feature/*` branches
- Pull Requests are used to merge features
- Merged branches are deleted to keep the repository clean

---

## 7. Agile Workflow
Although developed individually, the project simulates a **team-based Agile workflow**:
- Tasks are tracked using GitHub Projects
- Progress is visualized through board columns
- Each completed feature moves through the full lifecycle

---

## 8. Current Status
- Requirements analysis completed
- UML documentation completed
- Core entities and repositories implemented
- Controller and API layers are in progress
