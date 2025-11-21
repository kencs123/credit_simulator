# Credit Simulator

A vehicle credit simulator application built with Java and Maven.

#WebServices EndPoint Url: https://692026eb31e684d7bfcbe6f1.mockapi.io/api/v1/loanData/1

this repo will remain public after the test period is over

./bin/credit_simulator and ./credit_simulator is available on the attached zip file



## Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- Docker and Docker Compose (optional)

## Building the Project

### Using Maven


mvn clean package


### Using Docker Compose


docker-compose build


## Running the Application

### Method 1: Using the executable script

Make the script executable (Linux/Mac):

chmod +x bin/credit_simulator


Run without input file:

./bin/credit_simulator


Run with input file:

./bin/credit_simulator file_inputs.txt


### Method 2: Using Docker Compose


docker-compose run --rm credit-simulator


With input file:

docker-compose run --rm credit-simulator file_inputs.txt


### Method 3: Direct JAR execution


java -jar target/credit-simulator-jar-with-dependencies.jar



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

docker pull kencs123/credit-simulator:latest


Run the container:

docker run -it --rm kencs123/credit-simulator:latest


Run with input file:

docker run -it --rm -v $(pwd)/file_inputs.txt:/app/file_inputs.txt kencs123/credit-simulator:latest file_inputs.txt


