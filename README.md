# LNF Backend

Spring Boot backend for the Lost & Found application.

## Tech Stack

- Java 17
- Spring Boot 3.5
- Spring Web
- Spring Security
- Spring Data JPA
- MySQL
- Maven Wrapper (`mvnw`)

## Project Structure

- `src/main/java/com/thisara/LNF/controller` — REST controllers (`AuthController`, `ItemController`)
- `src/main/java/com/thisara/LNF/service` — business logic
- `src/main/java/com/thisara/LNF/repository` — JPA repositories
- `src/main/java/com/thisara/LNF/entity` — JPA entities and enums
- `src/main/resources/application.properties` — runtime configuration

## Prerequisites

- JDK 17+
- MySQL 8+
- Maven (optional, wrapper is included)

## Configuration

Update `src/main/resources/application.properties` for your environment.

Current important properties:

- `server.port=8090`
- `spring.datasource.url=jdbc:mysql://localhost:3306/lost_found_db`
- `spring.datasource.username=...`
- `spring.datasource.password=...`
- `spring.jpa.hibernate.ddl-auto=update`
- `jwt.secret=...`
- `jwt.expiration=86400000`

### Recommended (safer) local setup

Use environment variables instead of committing credentials in source:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/lost_found_db}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:password}
```

## Run Locally

From `LNF_Backend`:

### Windows (PowerShell)

```powershell
.\mvnw.cmd spring-boot:run
```

### macOS/Linux

```bash
./mvnw spring-boot:run
```

Backend starts on `http://localhost:8090`.

## Build JAR

```powershell
.\mvnw.cmd clean package
```

Expected output JAR:

- `target/lnf-proj.jar`

Run it:

```powershell
java -jar target/lnf-proj.jar
```

## API Overview

Base URL: `http://localhost:8090`

### Auth

#### Register

- `POST /api/auth/register`
- Body (JSON):

```json
{
  "username": "john",
  "email": "john@example.com",
  "password": "secret123",
  "contactInfo": "+94-77-1234567"
}
```

Response (success):

```json
{ "message": "User registered successfully!" }
```

#### Login

- `POST /api/auth/login`
- Body (JSON):

```json
{
  "username": "john",
  "password": "secret123"
}
```

Response (success):

```json
{
  "message": "Login successful!",
  "user": {
    "id": 1,
    "username": "john",
    "email": "john@example.com",
    "contactInfo": "+94-77-1234567"
  }
}
```

Response (failure):

```json
{ "message": "Invalid credentials" }
```

### Items

#### Create Item (multipart)

- `POST /api/items`
- Content-Type: `multipart/form-data`
- Parts:
  - `request` (JSON)
  - `imageFile` (file)

`request` example:

```json
{
  "title": "Black Wallet",
  "category": "ACCESSORIES",
  "description": "Found near library",
  "location": "Main Library",
  "date": "2026-03-01",
  "type": "FOUND",
  "imageUrl": "",
  "user": {
    "email": "john@example.com"
  }
}
```

#### Get All Items

- `GET /api/items`

#### Get Item by ID

- `GET /api/items/{id}`

#### Get Items by User ID

- `GET /api/items/user/{userid}`

#### Get Items by Category

- `GET /api/items/category/{category}`
- Supported categories:
  - `ELECTRONICS`
  - `DOCUMENTS`
  - `ACCESSORIES`
  - `CLOTHING`
  - `KEYS`
  - `BOOKS`
  - `OTHERS`

#### Update Item

- `PUT /api/items/{id}`
- Body: `ItemRequest` JSON

#### Delete Item

- `DELETE /api/items/{id}`

## Security & CORS

- Public endpoints currently include:
  - `/`
  - `/api/auth/register`
  - `/api/auth/login`
  - `/api/items/**`
- Session policy is stateless.
- CORS currently allows:
  - `http://localhost:*`
  - `https://*.vercel.app`

## Docker

Build image:

```powershell
docker build -t lnf-backend .
```

Run container:

```powershell
docker run --rm -p 8090:8090 --name lnf-backend lnf-backend
```

> Note: `Dockerfile` currently exposes port `8088`, while the app runs on `8090`. You can either:
> - change `server.port` to `8088`, or
> - update `Dockerfile` to expose/use `8090` consistently.

## Known Notes

- `GET /api/items/category/{category}` is marked with `// not working` in source and may require repository/service fixes.
- Authentication logic currently returns user data on successful login but does not issue a JWT token yet.

## Test

```powershell
.\mvnw.cmd test
```

---

If you also want, I can add a Postman collection file for these endpoints in this backend folder.