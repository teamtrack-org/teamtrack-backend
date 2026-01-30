# TeamTrack – Mini Jira Backend

TeamTrack is a mini project management application inspired by Jira.  
This project was developed as part of the **CSE 332 – Software Engineering** course, with a strong focus on **software engineering principles, documentation, and development process**, rather than only coding.

---

## 🚀 Technologies
- **Backend:** Java, Spring Boot
- **Database:** PostgreSQL
- **ORM:** JPA / Hibernate
- **Version Control:** Git & GitHub
- **Project Management:** GitHub Projects (Mini Jira)
- **Documentation:** SRS, UML (Use Case & Sequence)

---

## 🧠 Project Focus
This project emphasizes:
- Software Development Life Cycle (SDLC)
- Agile / Scrum-inspired workflow
- Clean architecture and layered design
- Proper documentation before implementation

---

## ✅ What Was Implemented So Far

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

### 🔹 Backend Design
- Designed a **layered Spring Boot backend architecture**.
- Implemented core domain entities:
  - `User`
  - `Project`
  - `Task`
- Defined entity relationships using **JPA annotations**.
- Implemented task lifecycle states:
  - `TODO`
  - `IN_PROGRESS`
  - `DONE`

### 🔹 Persistence Layer
- Implemented **Spring Data JPA repositories** for all entities.
- Ensured clean separation of concerns using packages:
  - `entity`
  - `repository`
  - `controller`

### 🔹 Version Control & Workflow
- Used **feature branch strategy** (`feature/*`).
- Opened and merged **Pull Requests** for each completed feature.
- Followed professional Git practices with meaningful commit messages.
- Cleaned up merged branches.

---

## 📂 Documentation
- 📄 [Software Requirements Specification (SRS)](docs/SRS.md)
- 📐 [UML Use Case Diagrams](docs/UML-UseCase.md)
- 🔄 [UML Sequence Diagrams](docs/UML-Sequence.md)
- 🧭 [SDLC Documentation](docs/SDLC.md)

---

## 🔮 Next Steps
- Implement REST controllers (Project & Task APIs)
- Add Swagger (OpenAPI) documentation
- Add basic authentication & authorization
- Write unit tests
