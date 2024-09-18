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
        			echo 'Pushing Docker image...'
        		
        			sh '''
        				echo $DOCKER_CREDENTIALS_PSW | docker login -u $DOCKER_CREDENTIALS_USR  --password-stdin
        				docker push muhumuzaivan/hospital-app:latest
        			'''
        		
        		}
        	}
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    try {
                        sh '''
                            echo $DOCKER_CREDENTIALS_PSW | docker login -u $DOCKER_CREDENTIALS_USR  --password-stdin
                            docker-compose down
                            docker-compose pull
                            docker-compose up -d
                            docker logout
                        '''
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
