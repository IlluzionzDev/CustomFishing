pipeline {
  agent any
  stages {
    stage('Build Jar') {
      steps {
        sh 'mvn clean install -U'
      }
    }
  }
}