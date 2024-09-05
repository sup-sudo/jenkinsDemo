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
                script {
                    def jarFile = "target/jenkinsdemo-0.0.1-SNAPSHOT.jar" // Define the JAR file name
                    def port = 8083 // Replace with the port your application should use

                    // Ensure the JAR file exists before proceeding
                    if (fileExists(jarFile)) {
                        echo "Deploying ${jarFile}..."

                        // Run the JAR file in the background on Windows
                        bat """
                            echo Starting application from ${jarFile} on port ${port}...
                            start /B java -jar ${jarFile}
                        """

                        // Optional: Monitor the application or check status here
                        sleep(time: 60, unit: 'SECONDS') // Wait to ensure the application starts properly

                        // Check if the application is running on the expected port
                        bat "curl -I http://localhost:${port}/api/v1/jenkins-demo/list" // Adjust the URL and endpoint as needed
                    } else {
                        error "JAR file not found: ${jarFile}"
                    }
                }
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
