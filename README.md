# Credit Simulator

A vehicle credit simulator application built with Java and Maven.

## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- Docker and Docker Compose (optional)

## Building the Project

### Using Maven

\`\`\`bash
mvn clean package
\`\`\`

### Using Docker Compose

\`\`\`bash
docker-compose build
\`\`\`

## Running the Application

### Method 1: Using the executable script

Make the script executable (Linux/Mac):
\`\`\`bash
chmod +x bin/credit_simulator
\`\`\`

Run without input file:
\`\`\`bash
./bin/credit_simulator
\`\`\`

Run with input file:
\`\`\`bash
./bin/credit_simulator file_inputs.txt
\`\`\`

### Method 2: Using Docker Compose

\`\`\`bash
docker-compose run --rm credit-simulator
\`\`\`

With input file:
\`\`\`bash
docker-compose run --rm credit-simulator file_inputs.txt
\`\`\`

### Method 3: Direct JAR execution

\`\`\`bash
java -jar target/credit-simulator-jar-with-dependencies.jar
\`\`\`

## Running Tests

\`\`\`bash
mvn test
\`\`\`

## CI/CD

This project uses GitHub Actions for continuous integration and deployment.

### Setup

1. Add the following secrets to your GitHub repository:
    - `DOCKER_USERNAME`: Your Docker Hub username
    - `DOCKER_PASSWORD`: Your Docker Hub password/token

2. Push to master/main branch to trigger the pipeline

### Pipeline stages:
- Build and compile
- Run unit tests
- Package application
- Build Docker image
- Push to Docker Hub

## Docker Image

Pull the latest image:
\`\`\`bash
docker pull <your-dockerhub-username>/credit-simulator:latest
\`\`\`

Run the container:
\`\`\`bash
docker run -it --rm <your-dockerhub-username>/credit-simulator:latest
\`\`\`

## Project Structure

\`\`\`
credit_simulator/
├── bin/                    # Executable scripts
├── src/
│   ├── main/
│   │   ├── java/          # Source code
│   │   └── resources/     # Resources
│   └── test/              # Unit tests
├── target/                # Build output
├── docker-compose.yml     # Docker Compose configuration
├── Dockerfile             # Docker image definition
├── pom.xml               # Maven configuration
└── README.md             # This file
\`\`\`
