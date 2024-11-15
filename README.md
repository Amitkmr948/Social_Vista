
# Social Media Backend

This project is the backend implementation for a **Social Media Application**. Built using **Java** and **Spring Boot**, it provides features like user authentication, photo posting, reel creation, and comment management. The application also supports real-time chat functionality, leveraging **RESTful APIs** to ensure smooth and efficient interaction with the database.

## Features

- User Authentication: Secure login and registration functionalities.
- Photo Posting: Upload and manage photos seamlessly.
- Reel Creation: Post and view engaging short videos.
- Comment Management: Add, view, and manage comments on posts.
- Real-time Chat: Communicate instantly using RESTful APIs.

## Tech Stack

- Backend Framework: Java, Spring Boot  
- Database: PostgreSQL  
- API Testing: Postman  
- Version Control: Git  

## Prerequisites

Ensure the following are installed on your system before running the project:

- Java 11 or later
- PostgreSQL
- Maven
- Git

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/your-username/social-media-backend.git
cd social-media-backend
```

### Configure the Database

1. Set up a PostgreSQL database.
2. Update the `application.properties` file located in the `src/main/resources/` directory with your database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your-database-name
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
```

### Build and Run the Application

1. Build the project using Maven:

   ```bash
   mvn clean install
   ```

2. Start the application:

   ```bash
   mvn spring-boot:run
   ```

The backend server will run on `http://localhost:8080`.

## API Documentation

Use Postman or any other API testing tool to interact with the backend. Below are some of the key API endpoints:

| Endpoint                  | Method | Description                          |
|---------------------------|--------|--------------------------------------|
| `/api/auth/register`      | POST   | Register a new user                 |
| `/api/auth/login`         | POST   | Log in an existing user             |
| `/api/posts`              | GET    | Retrieve all posts                  |
| `/api/posts`              | POST   | Create a new post                   |
| `/api/posts/{id}/comments`| POST   | Add a comment to a specific post    |
| `/api/chat/messages`      | GET    | Retrieve chat messages              |
| `/api/chat/messages`      | POST   | Send a chat message                 |

## Testing

Extensive testing of backend APIs has been conducted using POSTMAN to ensure reliable and efficient performance.

## Contributions

Contributions are welcome! Please fork this repository and submit a pull request for any features or bug fixes.

