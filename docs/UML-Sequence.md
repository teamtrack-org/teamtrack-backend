# Sequence Diagrams

## Use Case: Create Task

### Description
This sequence diagram represents the interaction between the user, frontend, backend, and database during the task creation process in TeamTrack.  
It shows the flow from filling the task form to storing the task in the database and updating the UI.

### Participants
- User
- Frontend
- Backend
- Database

### Flow (Step-by-Step)
1. **User** fills out the task form (title, project, etc.) on the **Frontend**.
2. **Frontend** sends a POST request to `/tasks` endpoint on the **Backend**.
3. **Backend** validates the request data.
4. **Backend** creates a new `Task` entity and assigns it to the correct `Project`.
5. **Backend** saves the `Task` entity to the **Database**.
6. **Database** confirms that the task is stored successfully.
7. **Backend** responds to **Frontend** with the created `Task` data.
8. **Frontend** updates the UI to show the new task.
9. **User** sees the new task in the project dashboard.

### Mermaid Diagram
```mermaid
sequenceDiagram
    participant User
    participant Frontend
    participant Backend
    participant Database

    User->>Frontend: Fill task form
    Frontend->>Backend: POST /tasks
    Backend->>Backend: Validate request
    Backend->>Database: Save Task
    Database-->>Backend: Confirm saved
    Backend-->>Frontend: Return Task
    Frontend-->>User: Display new Task

### Notes
- This sequence represents the **happy path** (no errors).  
- Error handling (invalid input, database failure) can be added later.  
- This diagram aligns with the **Project and Task entities** implemented in the backend.