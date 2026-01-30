# Database Schema Design

## User
- id (PK)
- username
- email
- password
- role

## Project
- id (PK)
- name
- description
- created_at

## Task
- id (PK)
- title
- description
- status
- project_id (FK)
- assigned_user_id (FK)
