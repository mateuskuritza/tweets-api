
## Project Roadmap
I started learning Java with this project. Below are listed the features that I intend to learn and implement

- [X] Http routes (verbs, params, body, etc.)
- [X] Data persistence (JPA) with Postgres using Docker when developing
- [X] DTO`s and Validation
- [X] Page/Pageable
- [X] Tests to services using in memory H2 database
- [X] Docs with Swagger: Available in SERVER_URL:PORT/swagger-ui/index.html (e.g. http://localhost:8080/swagger-ui/index.html)
- [X] Database migrations (Flyway)
- [ ] Exceptions Treatment
- [ ] Authentication: Maybe using Spring Security, JWT, AWS Cognito and/or Keycloack (must be role based)
- [ ] Terraform to provide AWS infra (vpc, subnet, database, ec2 or k8, etc.)
- [ ] Jenkins (ci and cd)
- [ ] Deploy AWS

## Project Setup Instructions

### Prerequisites

Before you begin, ensure you have the following installed on your machine:

- Java JDK 17 or later
- Maven (latest version recommended)
- Docker (to run the database)

#### Step 1: Clone the Repository

First, clone the repository to your local machine using the following command:

```bash
git clone https://github.com/your-username/your-repository.git
cd your-repository
```

#### Step 2: Install Dependencies

Navigate to the project directory and run the following command to install all required dependencies:

```bash
mvn clean install
```

This command will compile the project and download all necessary dependencies defined in the pom.xml file.

#### Step 3: Start the Database with Docker

Make sure Docker is running on your machine. The `spring-boot-docker-compose` dependency will start docker with the database when you run the application.

#### Step 4: Run the Application

You can start the Spring Boot application using the following command:

```bash
mvn spring-boot:run
```

Alternatively, you can build a runnable JAR file and execute it:

```bash
mvn package
java -jar target/your-artifact-name.jar
```

Replace `your-artifact-name.jar` with the actual name of the JAR file generated in the target directory.

#### Step 5: Access the Application

Once the application is running, you can access it at http://localhost:8080 (or any other port specified in your `application.properties` file).