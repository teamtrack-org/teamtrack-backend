# 🐳 Docker Setup Guide

This guide explains how to run the TeamTrack backend using Docker.

## Prerequisites

- Docker Desktop installed and running
- Docker Compose installed (usually comes with Docker Desktop)

## Quick Start

### 1. Build and Run with Docker Compose

```bash
# Build and start all services
docker-compose up --build

# Or run in detached mode (background)
docker-compose up -d --build
```

### 2. Access the Application

- **Backend API**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **pgAdmin**: http://localhost:5050
  - Email: `admin@teamtrack.com`
  - Password: `admin123`

### 3. Stop the Services

```bash
# Stop all services
docker-compose down

# Stop and remove volumes (clean database)
docker-compose down -v
```

## Services

### 🚀 Backend (Spring Boot)
- **Port**: 8080
- **Profile**: docker
- **Database**: PostgreSQL

### 🐘 PostgreSQL Database
- **Port**: 5432
- **Database**: teamtrack
- **Username**: teamtrack
- **Password**: teamtrack123

### 🔧 pgAdmin (Database Management)
- **Port**: 5050
- **Email**: admin@teamtrack.com
- **Password**: admin123

## Useful Commands

### View Logs
```bash
# All services
docker-compose logs -f

# Specific service
docker-compose logs -f backend
docker-compose logs -f postgres
```

### Restart Services
```bash
# Restart all
docker-compose restart

# Restart specific service
docker-compose restart backend
```

### Execute Commands in Container
```bash
# Access backend container
docker exec -it teamtrack-backend sh

# Access PostgreSQL
docker exec -it teamtrack-postgres psql -U teamtrack -d teamtrack
```

### Build Only (without running)
```bash
docker-compose build
```

### Remove Everything
```bash
# Stop and remove containers, networks, volumes
docker-compose down -v --remove-orphans
```

## Configuration

### Environment Variables

You can override environment variables in `docker-compose.yml`:

```yaml
environment:
  SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/teamtrack
  SPRING_DATASOURCE_USERNAME: teamtrack
  SPRING_DATASOURCE_PASSWORD: teamtrack123
```

### Profiles

The application uses the `docker` profile when running in Docker. Configuration is in:
- `src/main/resources/application-docker.properties`

## Troubleshooting

### Port Already in Use
If ports 8080, 5432, or 5050 are already in use, modify the ports in `docker-compose.yml`:

```yaml
ports:
  - "8081:8080"  # Change 8081 to any available port
```

### Database Connection Issues
1. Check if PostgreSQL is healthy:
   ```bash
   docker-compose ps
   ```
2. View PostgreSQL logs:
   ```bash
   docker-compose logs postgres
   ```

### Backend Won't Start
1. Check backend logs:
   ```bash
   docker-compose logs backend
   ```
2. Ensure PostgreSQL is running and healthy
3. Try rebuilding:
   ```bash
   docker-compose down
   docker-compose up --build
   ```

## Production Considerations

For production deployment:

1. **Change default passwords** in `docker-compose.yml`
2. **Use environment files** (`.env`) for sensitive data
3. **Enable SSL/TLS** for database connections
4. **Configure proper logging** and monitoring
5. **Set up backup strategies** for PostgreSQL data
6. **Use Docker secrets** for sensitive information
7. **Implement proper health checks**

## Health Checks

The backend includes health check endpoints:

```bash
# Check application health
curl http://localhost:8080/actuator/health
```

## Network

All services run on the `teamtrack-network` bridge network, allowing them to communicate using service names (e.g., `postgres`, `backend`).
