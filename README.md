# Hospital-information-System - Jenkins CI/CD Integration


[loom-jenkins-link](https://www.loom.com/share/1f7a53afda7e44e8929893d7aa41afc4)


This project demonstrates how to integrate Jenkins into the **Hospital Information System** using a Continuous Integration/Continuous Deployment (CI/CD) pipeline. The pipeline builds, tests, packages, and deploys a Dockerized Spring Boot application.

## Prerequisites

1. **Jenkins** installed and configured.
2. **Maven** installed (ensure Jenkins is configured to use Maven).
3. **Docker** installed (ensure Jenkins can access Docker).
4. **Docker Hub** (credentials stored in Jenkins).
5. **Jenkins Plugins**:
   - Docker Pipeline Plugin
   - Git Plugin
   - Maven Integration Plugin

## Pipeline Overview

The Jenkins pipeline performs the following steps:

1. **Build Stage**: Uses Maven to compile the source code.
2. **Test Stage**: Runs the project's tests using Maven.
3. **Package Stage**: Packages the application into a JAR file.
4. **Build Docker Image**: Builds a Docker image for the application.
5. **Push Docker Image**: Pushes the built image to Docker Hub.
6. **Deploy**: Uses Docker Compose to deploy the application.


## Jenkins Setup

1. **Install Plugins**: Ensure the required Jenkins plugins are installed.
2. **Create Credentials**: Store your Docker registry credentials in Jenkins:
   - Go to Jenkins Dashboard > Manage Jenkins > Manage Credentials > Add Credentials.
   - Add your Docker registry credentials and note the credentials ID (used in the pipeline as `DOCKER_CREDENTIALS`).
3. **Configure Maven**:
   - Go to Jenkins Dashboard > Manage Jenkins > Global Tool Configuration > Maven > Add Maven installation.
   - Set the name (e.g., `maven-latest`) to match the `tools` block in the pipeline.
4. **Configure Docker**: Ensure Jenkins has permission to run Docker commands.
   - Jenkins must have access to Docker on the server/agent it runs on.

## Running the Pipeline

1. Commit your `Jenkinsfile` to your project's root directory.
2. Set up a new Jenkins job with **Pipeline** type.
3. In the Jenkins job configuration, point it to your Git repository.
4. Run the pipeline job in Jenkins.

The pipeline will:
- Compile and package your Spring Boot application using Maven.
- Build a Docker image for the application.
- Push the image to your Docker registry.
- Deploy the application using Docker Compose.



