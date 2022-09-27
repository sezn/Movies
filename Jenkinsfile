pipeline {
    agent any

    stages {
        stage('Clean') {
            steps {
                sh './gradlew clean'
            }
        }
        stage('Build') {
            steps {
                sh './gradlew buildDebug'
            }
        }
        stage('Unit Test') {
            steps {
                sh './gradlew test'
            }
        }
        stage('UI Test') {
            steps {
                sh './gradlew connectedAndroidTest'
            }
        }
        stage('Assemble Apk') {
            steps {
                sh './gradlew assembleDebug'
            }
        }
    }

    post {
         always{
              archiveArtifacts artifacts: '**/*-debug.apk',
              onlyIfSuccessful: true
         }
    }
}