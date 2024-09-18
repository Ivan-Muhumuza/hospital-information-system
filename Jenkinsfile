pipeline {
    agent any

    tools {
        maven 'maven-latest' // Use the Maven installation defined in Jenkins
        // dockerTool 'docker-latest'
    }
    
    environment {
        DOCKER_CREDENTIALS = credentials('7997ef30-d000-42de-aeaa-05a65c902406') // DockerHub credentials stored in Jenkins
    }
    
    stages {
        
        stage('Build') {
            steps {
                // Build the Java application with Maven
                sh 'mvn clean package -DskipTests'
                echo 'maven clean ran'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t muhumuzaivan/hospital-app:latest .'
                echo 'Docker image built'
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: '7997ef30-d000-42de-aeaa-05a65c902406', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    script {
                        sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin docker.io'
                        sh 'docker push muhumuzaivan/hospital-app:latest'
                        sh 'docker logout'
                    }
                }
                echo 'Docker image pushed'
            }
        }
    
        stage('Deploy with Docker Compose') {
            steps {
                script {
                    // Pull the latest image and deploy using Docker Compose
                    sh 'docker-compose down'  // Stop existing containers
                    sh 'docker-compose pull'  // Pull the latest image
                    sh 'docker-compose up -d'  // Start new containers in detached mode
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
