pipeline {
    agent any

    tools {
        // Use local installations of Maven and JDK
        maven 'Maven3' // Change this to the name of your Maven installation in Jenkins
        jdk 'JDK17'   // Change this to the name of your JDK installation in Jenkins
    }

    environment {
        // Environment variables
        MAVEN_OPTS = '-Dmaven.test.failure.ignore=true'
    }

    stages {
        stage('Checkout') {
            steps {
                // Check out the code from your local Git repository
                git 'file:///path/to/your/local/repo.git'
            }
        }

        stage('Build') {
            steps {
                // Clean and build the project using Maven
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                // Run the tests using Maven
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                // Package the application
                sh 'mvn package'
            }
        }

        stage('Deploy') {
            steps {
                // Deploy the application locally
                // Example: Copy the JAR file to a local directory
                sh 'cp target/your-application.jar /path/to/deployment/directory'
                // You can start the application here as well
                // sh 'java -jar /path/to/deployment/directory/your-application.jar'
            }
        }
    }

    post {
        always {
            // Clean up the workspace after the pipeline
            cleanWs()
        }

        success {
            echo 'Build, test, and deployment successful!'
        }

        failure {
            echo 'Build, test, or deployment failed!'
       }
    }
}