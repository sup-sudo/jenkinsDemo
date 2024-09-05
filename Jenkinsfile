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
        stage('Build') {
            steps {
                // Clean and build the project using Maven (for Windows)
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                // Run the tests using Maven (for Windows)
                bat 'mvn test'
            }
        }
    }

    post {
        always {
            // Clean up the workspace after the pipeline
            cleanWs()
        }

        success {
            echo 'Build and test successful!'
        }

        failure {
            echo 'Build or test failed!'
        }
    }
}
