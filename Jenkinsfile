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
                    def jarFile = "target/jenkinsdemo-0.0.1-SNAPSHOT.jar" // Replace with your actual JAR file name

                    // Ensure the JAR file exists before proceeding
                    if (fileExists(jarFile)) {
                        echo "Deploying ${jarFile}..."

                        // Example: Copy the JAR file to a remote server using SCP
                        // Note: You may need to configure credentials for this
                        // Replace with your server details
                        sh "scp ${jarFile} user@remote-server:/path/to/deploy/"

                        // Example: Run the JAR file on the remote server (if needed)
                        // sh "ssh user@remote-server 'java -jar /path/to/deploy/${jarFile}'"
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
