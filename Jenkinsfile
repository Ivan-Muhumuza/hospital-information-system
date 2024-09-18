pipeline {
    agent any

    tools {
        maven 'maven-latest'
    }

    environment {
        DOCKER_CREDENTIALS = credentials('7997ef30-d000-42de-aeaa-05a65c902406')
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
                echo 'Maven build completed'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    try {
                        sh 'docker build -t muhumuzaivan/hospital-app:latest .'
                        echo 'Docker image built successfully'
                    } catch (Exception e) {
                        error "Failed to build Docker image: ${e.message}"
                    }
                }
            }
        }

        // stage('Push Docker Image') {
        //     steps {
        //         script {
        //             try {
        //                 sh 'docker login'
        //                 sh 'docker push muhumuzaivan/hospital-app:latest'
        //                 echo 'Docker image pushed successfully'
        //             } catch (Exception e) {
        //                 error "Failed to push Docker image: ${e.message}"
        //             }
        //         }
        //     }
        // }

        stage('Push Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: '7997ef30-d000-42de-aeaa-05a65c902406', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh '''
                            echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin
                            docker push muhumuzaivan/hospital-app:latest
                        '''
                    }
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    try {
                        sh 'docker-compose down'
                        sh 'docker-compose pull'
                        sh 'docker-compose up -d'
                        sh 'docker logout'
                        echo 'Deployment completed successfully'
                    } catch (Exception e) {
                        error "Deployment failed: ${e.message}"
                    }
                }
            }
        }
    }

    post {
        always {
            echo "Pipeline completed!"
        }
        success {
            echo "Pipeline succeeded!"
        }
        failure {
            echo "Pipeline failed!"
        }
    }
}
