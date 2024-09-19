# Kubernetes lab 

[loom-link-kubernetes](https://www.loom.com/share/618c7919d76d498190d5dd4f7f02c410)
## Project Overview

This project demonstrates the following:
- Set up a Kubernetes cluster using Minikube.
- Deploy a hospital information system using Kubernetes deployments.
- Expose the application using Kubernetes services.

## Prerequisites
Before running the project, ensure you have the following installed:
- **Minikube**: For running a local Kubernetes cluster. Follow the [installation guide](https://minikube.sigs.k8s.io/docs/start/).
- **Docker**: For containerizing the Java application.
- **kubectl**: Command-line tool to interact with your Kubernetes cluster.
- **Maven**: To build the Java project.

## Setting up Kubernetes Cluster
1. Start Minikube:
    ```bash
    minikube start
    ```

2. Verify Minikube status:
    ```bash
    minikube status
    ```

## Building the Java Application

1. Build the Docker image for your application:
    ```bash
    eval $(minikube docker-env)
    docker build -t muhumuzaivan/hospital-app:latest .
    ```

## Deploying the Application to Kubernetes
1. Apply the Kubernetes deployment:
    ```bash
    kubectl apply -f deployment.yaml
    ```

2. Verify the deployment:
    ```bash
    kubectl get deployments
    kubectl get pods
    ```

3. Apply the Kubernetes service to expose the application:
    ```bash
    kubectl apply -f service.yaml
    ```

4. Verify the service:
    ```bash
    kubectl get services
    ```

## Accessing the Application
1. Run the following command to open the service in your browser:
    ```bash
    minikube service hospital-app-service
    ```
