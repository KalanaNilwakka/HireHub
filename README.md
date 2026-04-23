# HireHub

A modern, full-featured hiring and recruitment management platform built with Spring Boot and Java. HireHub streamlines the recruitment process by providing tools for job posting, candidate management, and hiring workflow automation.

## 🚀 Features

* **Job Management**: Create, edit, and manage job postings
* **Candidate Tracking**: Track candidate applications and recruitment status
* **User Authentication & Security**: Secure login with JWT-based authentication
* **Role-Based Access Control**: Different access levels for admins, recruiters, and candidates
* **Data Validation**: Comprehensive input validation across all endpoints
* **RESTful API**: Well-designed REST endpoints for seamless integration

## 🛠️ Tech Stack

* **Backend Framework**: Spring Boot 3.5.3
* **Language**: Java 21
* **Database**: MySQL
* **ORM**: Spring Data JPA
* **Security**: Spring Security with JWT authentication
* **API**: RESTful Web Services
* **Build Tool**: Maven
* **Additional Libraries**:

    * Lombok (for reducing boilerplate code)
    * ModelMapper (for object mapping)
    * JSON Web Token (JWT) for secure token generation and validation

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

* Java Development Kit (JDK) 21 or higher
* Maven 3.6 or higher
* MySQL 8.0 or higher
* Git

## 🔧 Installation

1. **Clone the repository**

```bash
  git clone https://github.com/KalanaNilwakka/HireHub.git
  cd HireHub
```

2. **Configure the database**

Create a MySQL database for HireHub.

Update `application.properties` or `application.yml` with your database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hirehub
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

3. **Build the project**

```bash
  mvn clean install
```

4. **Run the application**

```bash
  mvn spring-boot:run
```

Or using the Maven wrapper:

```bash
  ./mvnw spring-boot:run
```

The application will start on `http://localhost:8080` by default.

## 📚 API Documentation

The application exposes RESTful endpoints for managing:

* Jobs
* Candidates
* Applications
* Users and Authentication

All API requests should include appropriate JWT tokens in the `Authorization` header for protected endpoints.

## 🔐 Authentication

HireHub uses JWT (JSON Web Token) for secure authentication. Include your JWT token in the Authorization header:

```text
Authorization: Bearer YOUR_JWT_TOKEN_HERE
```

## 📁 Project Structure

```text
HireHub/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/Kalana/HireHub/
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── repository/
│   │   │       ├── model/
│   │   │       ├── dto/
│   │   │       ├── config/
│   │   │       ├── exception/
│   │   │       ├── security/
│   │   │       ├── util/
│   │   │       └── HireHubApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## 👤 Author

Kalana Nilwakka

GitHub: @KalanaNilwakka

## 📞 Support

For questions or support, please open an issue in the GitHub repository.
