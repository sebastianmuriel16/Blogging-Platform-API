# 📚 Blogging Platform API
https://roadmap.sh/projects/blogging-platform-api

🚀 Running the Project
1. Clone the Repository
   git clone https://github.com/usuario/blogging-platform-api.git
   cd blogging-platform-api

2. Configure Database (MySQL)

Edit src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/blogging_platform
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

3. Build and Run
   mvn spring-boot:run


REST API built with **Spring Boot 3** for managing blog posts (**BlogPosts**) and their tags (**Tags**).  
It includes functionalities to create, list, update, delete, and search posts, with validations and custom exception handling.

---

## 🛠 Technologies Used
- **Java 17**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **Jakarta Validation**
- **Lombok**
- **Hibernate**
- **MySQL** (configurable)
- **Maven**

---

## 📂 Project Architecture

The API follows a layered architecture:

- **Controller** (`BlogPostController`): Handles HTTP requests and defines endpoints.
- **Service** (`BlogPostService` / `BlogPostServiceImpl`): Contains business logic.
- **Repository** (`BlogPostRepositorie`, `TagRepositorie`): Data access layer with Spring Data JPA.
- **Entity** (`BlogPost`, `Tag`): Database table representation.
- **DTOs** (`BlogPostDTO`, `TagDTO`, `SimpleTagDTO`): Data transfer objects with validations.

---

## 🔗 Endpoints

### 1. List BlogPosts
**GET** `/api/v1/post`

**Optional Parameters:**
- `term` *(String)* — filters by title, content, or category (case-insensitive partial search).

**Example:**
```bash
curl -X GET "http://localhost:8080/api/v1/post?term=java"
Response (200 OK):

json
Copiar
Editar
[
  {
    "id": 1,
    "title": "Spring Boot Basics",
    "content": "Introduction to Spring Boot",
    "category": "Programming",
    "tags": [{ "id": 1, "name": "Java" }],
    "createdAt": "2025-08-14T18:30:00",
    "updatedAt": "2025-08-14T18:30:00"
  }
]
2. Get BlogPost by ID
GET /api/v1/post/{id}

Response (200 OK):

json
Copiar
Editar
{
  "id": 1,
  "title": "Spring Boot Basics",
  "content": "Introduction to Spring Boot",
  "category": "Programming",
  "tags": [{ "id": 1, "name": "Java" }],
  "createdAt": "2025-08-14T18:30:00",
  "updatedAt": "2025-08-14T18:30:00"
}
Errors:

404 Not Found: If the post does not exist.

3. Create BlogPost
POST /api/v1/post

Request Body:

json
Copiar
Editar
{
  "title": "New Post",
  "content": "Post content",
  "category": "Technology",
  "tags": [{ "name": "Java" }, { "name": "Spring" }]
}
Response (201 Created):

json
Copiar
Editar
{
  "id": 2,
  "title": "New Post",
  "content": "Post content",
  "category": "Technology",
  "tags": [
    { "id": 1, "name": "Java" },
    { "id": 2, "name": "Spring" }
  ],
  "createdAt": "2025-08-14T18:40:00",
  "updatedAt": "2025-08-14T18:40:00"
}
The Location header contains the URL of the newly created resource.

Validations:

title, content, category: Cannot be null or empty.

tags: Must contain at least one entry (@NotEmpty).

4. Update BlogPost
PUT /api/v1/post/{id}

Request Body:

json
Copiar
Editar
{
  "title": "Updated Post",
  "content": "New content",
  "category": "Programming",
  "tags": [{ "name": "Spring Boot" }]
}
Response (200 OK):

json
Copiar
Editar
{
  "id": 1,
  "title": "Updated Post",
  "content": "New content",
  "category": "Programming",
  "tags": [{ "id": 3, "name": "Spring Boot" }],
  "createdAt": "2025-08-14T18:30:00",
  "updatedAt": "2025-08-14T18:45:00"
}
Errors:

404 Not Found: If the post does not exist.

400 Bad Request: If validations fail.

5. Delete BlogPost
DELETE /api/v1/post/{id}

Response:

204 No Content: Successfully deleted.

404 Not Found: If the post does not exist.

🔍 Tag Creation/Update Flow
The client sends a list of tags in the DTO.

The service searches each tag by name in the database.

If the tag does not exist, it is created automatically.

The tag set is linked to the BlogPost.

The BlogPost is saved to the database.

⚠️ Error Handling
The API uses CustomException to throw custom errors with message and HTTP status code.
Example:

json
Copiar
Editar
{
  "error": "Blog post with id 99 not found",
  "status": 404
}
🚀 Running the Project
1. Clone the Repository
bash
Copiar
Editar
git clone https://github.com/usuario/blogging-platform-api.git
cd blogging-platform-api
2. Configure Database (MySQL)
Edit src/main/resources/application.properties:

properties
Copiar
Editar
spring.datasource.url=jdbc:mysql://localhost:3306/blogging_platform
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
3. Build and Run
bash
Copiar
Editar
mvn spring-boot:run
API available at:

bash
Copiar
Editar
http://localhost:8080/api/v1/post
🧪 Example Tests with cURL
Create a post:

bash
Copiar
Editar
curl -X POST http://localhost:8080/api/v1/post \
-H "Content-Type: application/json" \
-d '{
  "title": "My First Post",
  "content": "Sample content",
  "category": "Blog",
  "tags": [{ "name": "Java" }]
}'
Search by term:

bash
Copiar
Editar
curl "http://localhost:8080/api/v1/post?term=java"