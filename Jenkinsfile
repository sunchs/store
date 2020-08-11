pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "mvn -Dmaven.test.failure.ignore clean install"
            }
        }
    }
    post {
        success {
            script {
                echo "构建成功"
            }
        }
        failure {
            script {
                echo "构建失败"
            }
        }
    }
}