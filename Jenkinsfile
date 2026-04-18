pipeline {
    agent any   // ✅ correct usage

    tools {
        maven 'Maven'   // Make sure Maven is configured in Jenkins
    }

    stages {

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Verify JAR') {
            steps {
                sh 'ls target'
            }
        }

        stage('Run Application') {
            steps {
                sh 'java -jar target/*.jar'
            }
        }
    }

    post {
        failure {
            echo 'Build failed! ❌'
        }
        success {
            echo 'Build successful! ✅'
        }
    }
}
