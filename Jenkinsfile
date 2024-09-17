pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS = credentials('7997ef30-d000-42de-aeaa-05a65c902406') // DockerHub credentials stored in Jenkins
    }

    stages {
        stage('Build') {
            steps {
                // Build the Java application with Maven
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image using your Dockerfile
                    docker.build("muhumuzaivan/hospital-app:latest")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Push the Docker image to Docker Hub
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS) {
                        docker.image("muhumuzaivan/hospital-app:latest").push()
                    }
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    // Pull the latest image and deploy using Docker Compose
                    sh 'docker-compose down'  // Stop existing containers
                    sh 'docker-compose pull'  // Pull the latest image
                    sh 'docker-compose up -d'  // Start new containers in detached mode
                }
            }
        }
    }

    post {
        always {
            echo "Pipeline completed!"
        }
    }
}
