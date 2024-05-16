pipeline{
    agent any
    stages{
        stage('Build'){
            steps{
                echo "Esta etapa BUILD no disponible"
            }
        }
        stage('Tests'){
            steps{
                echo "Esta etapa TESTS no disponible"
            }
        }
        stage('Deploy'){
            steps{
                sh "docker-compose down -v"
                sh "docker-compose up -d --build"
            }
        }
    }
}