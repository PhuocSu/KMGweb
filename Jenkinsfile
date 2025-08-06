pipeline {
    agent any

    triggers {
        pollSCM('H/5 * * * *')  // kiểm tra repo mỗi 5 phút
    }

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'jenkins-702prime',
                    url: 'https://github.com/rayrobin/kgmmall_fe.git',
                    branch: 'main' 
            }
        }

        stage('Build & Test') {
            steps {
                echo "Chạy test script..."
                // Ví dụ chạy Maven
                sh 'mvn clean test'
            }
        }
    }
}
