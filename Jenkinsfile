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

        stage('Deploy') {
            steps {
                // Assuming your JAR file is always in target/ after build
                def jarFile = "target/your-application.jar" // Replace with your actual JAR file name

                // Run the application on the specified port
                bat """
                    echo Starting application from ${jarFile}...
                    start java -jar ${jarFile}
                """
            }
        }
    }

    post {
        always {
            // Kill any Java process related to the application
            bat 'taskkill /F /IM java.exe /T'

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
