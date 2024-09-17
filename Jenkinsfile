pipeline {
    agent any

    tools {
        maven 'maven-latest' // Use the Maven installation defined in Jenkins
        dockerTool 'docker-latest'
    }
    
    environment {
        DOCKER_CREDENTIALS = credentials('7997ef30-d000-42de-aeaa-05a65c902406') // DockerHub credentials stored in Jenkins
    }

    stages {

        // stage('Init') {
        //     steps {
        //         script {
        //             // Retrieve Docker and Maven tools from Jenkins Global Tool Configuration
        //             def dockerHome = tool name: 'docker-latest', type: 'com.nirima.jenkins.plugins.docker.DockerTool'
        //             def mavenHome = tool name: 'maven-latest', type: 'hudson.tasks.Maven$MavenInstallation'

        //             // Update the PATH environment variable within the script block
        //             env.PATH = "${dockerHome}/bin:${mavenHome}/bin:${env.PATH}"

        //         }
        //     }
        // }
    
    
        stage('Build') {
            steps {
                // Build the Java application with Maven
                // sh 'mvn clean package -DskipTests'
                echo 'building now'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image using your Dockerfile
                    // docker.build("muhumuzaivan/hospital-app:latest")
                    echo  'docker  image'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Push the Docker image to Docker Hub
                    // docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS) {
                    //     docker.image("muhumuzaivan/hospital-app:latest").push()
                    echo 'pushing image'
                    }
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    // Pull the latest image and deploy using Docker Compose
                    // sh 'docker-compose down'  // Stop existing containers
                    // sh 'docker-compose pull'  // Pull the latest image
                    // sh 'docker-compose up -d'  // Start new containers in detached mode
                    echo  'deploying image now'
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
