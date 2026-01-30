# Software Requirements Specification (SRS)

## 1. Introduction
**Project Name:** TeamTrack  
**Purpose:** TeamTrack is a mini project management application (Mini Jira) designed to manage tasks and projects within a team.  
**Scope:** This SRS document covers the functional and non-functional requirements for the backend system, including User, Project, and Task entities.  

---

## 2. Functional Requirements

### 2.1 User Management
- Users can register and log in.
- Each user has a role: `ADMIN` or `USER`.
- Admin users can create projects and assign tasks.

### 2.2 Project Management
- Users can create projects.
- Projects are owned by a User.
- Each project can have multiple tasks.

### 2.3 Task Management
- Users can create tasks within a project.
- Each task has a title and a status (`TODO`, `IN_PROGRESS`, `DONE`).
- Tasks are linked to a project and can be updated or deleted.

---

## 3. Non-Functional Requirements

### 3.1 Technology Stack
- **Backend:** Java + Spring Boot
- **Database:** PostgreSQL
- **Frontend:** React (not covered in backend SRS)
- **Version Control:** Git with feature branching

### 3.2 Performance
- Backend should handle concurrent task creation efficiently.
- Database operations should be ACID-compliant.

### 3.3 Security
- Passwords should be securely stored (hashed).
- Role-based access control for Admin/User operations.

### 3.4 Maintainability
- Entities and relationships follow JPA annotations.
- Modular package structure (`entity`, `repository`, `controller`).

---

## 4. Entity Mapping (Reference)
- **User** ↔ owns → **Project**  
- **Project** ↔ contains → **Task**  

---

## 5. UML References
- **Use Case Diagrams:** Shows actors, use cases, and interactions. [View UML-UseCase.md](./UML-UseCase.md)  
- **Sequence Diagrams:** Shows step-by-step flow of key operations like task creation. [View UML-Sequence.md](./UML-Sequence.md)

---

## 6. Assumptions and Dependencies
- The system assumes a reliable PostgreSQL database.
- Backend APIs are consumed by a React frontend.
- Users have valid credentials to access the system.
