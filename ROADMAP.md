# TeamTrack Backend - Roadmap

## Current Version: v1.0.0

---

## 📋 v1.0.1 - Planned Features

### 🔴 **Critical Security & Authentication**

#### 1.1 JWT Authentication Implementation
- [ ] Create `JwtTokenProvider` service
- [ ] Implement token generation
- [ ] Implement token validation
- [ ] Add refresh token mechanism
- [ ] Create `/api/auth/login` endpoint (returns JWT)
- [ ] Update `SecurityConfig` to use JWT filter
- [ ] Add token expiration handling

#### 1.2 Password Validation
- [ ] Add password validation in `RegisterRequest`
  - Minimum 8 characters
  - At least 1 uppercase letter
  - At least 1 lowercase letter
  - At least 1 number
  - Optional: Special character
- [ ] Create custom `@ValidPassword` annotation
- [ ] Add validation error messages

#### 1.3 Email Validation
- [ ] Add `@Email` annotation to User entity
- [ ] Add email format validation in DTOs
- [ ] Prevent duplicate email registration (already exists)

#### 1.4 Error Handling Standardization
- [ ] Create `ErrorResponse` DTO
- [ ] Standardize HTTP status codes
- [ ] Extend `GlobalExceptionHandler`
- [ ] Add validation error details
- [ ] Add timestamp and request path to errors

---

### 🟡 **Task Management Enhancements**

#### 2.1 Task Assignment
- [ ] Add `assignedTo` field to `Task` entity (ManyToOne with User)
- [ ] Update `TaskRequestDto` with `assignedToId`
- [ ] Update `TaskResponseDto` with assignee details
- [ ] Create `POST /api/tasks/{id}/assign` endpoint
- [ ] Create `DELETE /api/tasks/{id}/unassign` endpoint
- [ ] Add validation for assignee existence

#### 2.2 Task Priority
- [ ] Create `Priority` enum (LOW, MEDIUM, HIGH, CRITICAL)
- [ ] Add `priority` field to `Task` entity
- [ ] Update DTOs with priority
- [ ] Add priority filtering in `TaskRepository`
- [ ] Add sorting by priority

#### 2.3 Task Due Date
- [ ] Add `dueDate` field to `Task` entity (LocalDateTime)
- [ ] Update DTOs with dueDate
- [ ] Create endpoint to get overdue tasks
- [ ] Add date range filtering

#### 2.4 Task Comments (Optional)
- [ ] Create `Comment` entity
- [ ] ManyToOne relationship with Task
- [ ] ManyToOne relationship with User (author)
- [ ] CRUD endpoints for comments

---

### 🟢 **User Management**

#### 3.1 User Profile
- [ ] Create `GET /api/users/me` endpoint (current user)
- [ ] Create `PUT /api/users/me` endpoint (update profile)
- [ ] Create `PUT /api/users/me/password` endpoint (change password)
- [ ] Add password confirmation validation

#### 3.2 User Management (Admin)
- [ ] Create `GET /api/users` endpoint (ADMIN only)
- [ ] Create `PUT /api/users/{id}/role` endpoint (change role)
- [ ] Create `DELETE /api/users/{id}` endpoint (deactivate user)
- [ ] Add `active` flag to User entity

---

### 🔵 **Project Management Enhancements**

#### 4.1 Project Members
- [ ] Create `ProjectMember` entity (join table)
- [ ] Add `members` field to Project (ManyToMany)
- [ ] Create `POST /api/projects/{id}/members` endpoint
- [ ] Create `DELETE /api/projects/{id}/members/{userId}` endpoint
- [ ] Update authorization to check membership

#### 4.2 Project Status
- [ ] Create `ProjectStatus` enum (ACTIVE, ON_HOLD, COMPLETED, ARCHIVED)
- [ ] Add `status` field to Project entity
- [ ] Add status filtering
- [ ] Add `PATCH /api/projects/{id}/status` endpoint

#### 4.3 Project Dates
- [ ] Add `startDate` and `endDate` to Project entity
- [ ] Update DTOs with dates
- [ ] Add date validation (endDate > startDate)

---

### 🔧 **Technical Improvements**

#### 5.1 Logging
- [ ] Add SLF4J logging to all services
- [ ] Add request/response logging interceptor
- [ ] Configure log levels per environment
- [ ] Add structured logging (JSON format)

#### 5.2 Caching
- [ ] Add Spring Cache abstraction
- [ ] Cache project list
- [ ] Cache user details
- [ ] Configure cache eviction policies

#### 5.3 Rate Limiting
- [ ] Add Bucket4j dependency
- [ ] Implement rate limiting filter
- [ ] Configure per-user limits
- [ ] Return 429 status for exceeded limits

#### 5.4 API Versioning
- [ ] Refactor endpoints to `/api/v1/...`
- [ ] Add version header support
- [ ] Document versioning strategy

#### 5.5 Database Optimizations
- [ ] Add index on `User.email`
- [ ] Add index on `Project.ownerId`
- [ ] Add index on `Task.status`
- [ ] Add index on `Task.projectId`
- [ ] Optimize N+1 queries with fetch joins

#### 5.6 Soft Delete
- [ ] Add `deleted` flag to entities
- [ ] Add `deletedAt` timestamp
- [ ] Update repositories to filter deleted records
- [ ] Add `@Where` annotation to entities

#### 5.7 Audit Trail
- [ ] Enable JPA Auditing
- [ ] Add `@CreatedDate` and `@LastModifiedDate`
- [ ] Add `@CreatedBy` and `@LastModifiedBy`
- [ ] Create `AuditingConfig`

---

### 🔍 **Search & Filter**

#### 6.1 Global Search
- [ ] Add search by project name/description
- [ ] Add search by task title/description
- [ ] Implement full-text search (optional: Elasticsearch)

#### 6.2 Advanced Filtering
- [ ] Add `Specification` API for dynamic queries
- [ ] Support multiple filter combinations
- [ ] Add sorting options (name, date, status)

---

### 📊 **Analytics & Reporting**

#### 7.1 Dashboard Endpoints
- [ ] Create `GET /api/dashboard/stats` endpoint
  - Total projects
  - Total tasks
  - Completed tasks
  - Overdue tasks
- [ ] Create `GET /api/dashboard/recent-activity` endpoint

#### 7.2 Project Statistics
- [ ] Add task count to project response
- [ ] Add completion percentage
- [ ] Add member count

---

### 🧪 **Testing**

#### 8.1 Increase Test Coverage
- [ ] Add controller integration tests for all endpoints
- [ ] Add service layer edge case tests
- [ ] Add security tests (unauthorized access)
- [ ] Add validation tests
- [ ] Target: 80%+ code coverage

#### 8.2 Performance Testing
- [ ] Add load testing with JMeter/Gatling
- [ ] Benchmark critical endpoints
- [ ] Optimize slow queries

---

### 📚 **Documentation**

#### 9.1 API Documentation
- [ ] Add request/response examples to all Swagger endpoints
- [ ] Add error response examples
- [ ] Create authentication guide
- [ ] Add Postman collection

#### 9.2 Code Documentation
- [ ] Add JavaDoc to all public methods
- [ ] Document complex business logic
- [ ] Add architecture decision records (ADR)

---

### 🚀 **DevOps**

#### 10.1 CI/CD Pipeline
- [ ] Create GitHub Actions workflow
- [ ] Automated testing on PR
- [ ] Automated deployment to staging
- [ ] Docker image build and push

#### 10.2 Monitoring
- [ ] Add Spring Boot Actuator metrics
- [ ] Configure Prometheus endpoints
- [ ] Add custom health indicators
- [ ] Set up alerting

---

## 🎯 Quick Wins for v1.0.1 (Priority)

1. ✅ Password validation (30 min)
2. ✅ Email validation (20 min)
3. ✅ Error response standardization (1 hour)
4. ✅ Audit trail (createdAt, updatedAt) (1 hour)
5. ✅ Database indexing (30 min)
6. ✅ Logging improvements (1 hour)

**Total Estimated Time:** ~4-5 hours

---

## 📅 Release Timeline

- **v1.0.1** - Target: 2 weeks
  - Focus: Security, Task Management, UX
- **v1.0.2** - Target: 1 month
  - Focus: Project Members, Search, Analytics
- **v1.1.0** - Target: 2 months
  - Focus: Advanced features, Performance, DevOps

---

## 🔗 Related Documents

- [README.md](README.md) - Project overview
- [DOCKER.md](DOCKER.md) - Docker setup
- [Frontend Roadmap](../teamtrack-frontend/ROADMAP.md)
