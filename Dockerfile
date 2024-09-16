# Step 1: Use a base image that includes Java and Maven
FROM eclipse-temurin:21

# Step 2: Set the working directory in the container
WORKDIR /app

# Step 3: Copy the JAR file from the host machine to the container
COPY target/lab-0.0.1-SNAPSHOT.jar /app/lab-0.0.1-SNAPSHOT.jar

# Step 4: Expose the port that the Spring Boot app will run on
EXPOSE 8080

# Step 5: Define the command to run the Spring Boot application
CMD ["java", "-jar", "/app/lab-0.0.1-SNAPSHOT.jar"]
