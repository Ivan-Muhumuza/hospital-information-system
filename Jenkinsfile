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
                sh 'mvn clean compile'
                echo 'Maven build completed'
            }
        }

        stage('Test') {
            steps {
                script {
                    def hasTests = sh(script: 'find src/test/java -name "*.java" | wc -l', returnStdout: true).trim()
                    if (hasTests != "0") {
                        echo "Running tests..."
                        sh 'mvn test'
                    } else {
                        echo "No tests found, skipping test stage"
                    }
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
                echo 'Maven package completed'
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
