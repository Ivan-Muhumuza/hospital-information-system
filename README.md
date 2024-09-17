# hospital-information-system with Docker


[loom-video-link](https://www.loom.com/share/b345caab95a14ff6a926f24c748fe1c4)


## Overview

This is a simple Spring Boot application containerized using Docker. The application is connected to MongoDB, and Redis services using Docker Compose for multi-container orchestration.

## Technologies Used

- **Spring Boot** for the application framework
- **MongoDB** NoSQL database for storing hospital data
- **Redis** for caching frequently accessed data.
- **Docker** to containerize the application and its dependencies.
- **Docker Compose** for multi-container orchestration(to define and run a multi-container setup.)

## Application Configuration

The application connects to MongoDB, & Redis with the following configurations:

- **MongoDB**: `mongodb://mongo:27017/hospital`
- **Redis**: `redis://localhost:6379`

## Prerequisites

- Docker installed on your machine
- Docker Compose installed

## How to Build and Run the Application

1. **Build the Spring Boot application**:
   ```bash
   mvn clean package
   ```

2. **Build and start the containers**:
   Use Docker Compose to build the Docker image and start all containers (Spring Boot app, MongoDB, and Redis):
   ```bash
   docker-compose up --build
   ```

3. **Access the application**:
    - The Spring Boot application will be available at `http://localhost:8080`.
    - The MongoDB service will be available at `localhost:27017`.
    - The Redis service will be available at `localhost:6379`.




