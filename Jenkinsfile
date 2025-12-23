pipeline {
  agent any

  parameters {
    choice(name: 'ENV', choices: ['dev', 'qa', 'prod'], description: 'Target environment')
  }

  environment {
    IMAGE_NAME = 'springboot-app-deployment'
    IMAGE_TAG  = "${env.BUILD_NUMBER}"
    YOUR_GIT_REPO_URL='https://github.com/sumanthrao04/springboot-app-deployment.git'
  }

  tools {
    maven 'maven-3.9'
  }

  stages {
    stage('Checkout') {
      steps {
        git branch: 'master',
            changelog: false,
            poll: false,
            url: YOUR_GIT_REPO_URL
      }
    }

    stage('Clean') {
      steps {
        sh 'mvn -U clean'
      }
    }

    stage('Approval') {
      when { expression { params.ENV == 'prod' } }
      steps {
        timeout(time: 30, unit: 'MINUTES') {
          input(
            message: "Approve to continue to TEST/BUILD for ENV=${params.ENV} (Build #${env.BUILD_NUMBER})?",
            ok: 'Approve',
            submitter: 'sparashuram'
          )
        }
      }
    }

    stage('Test (JUnit + JaCoCo)') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Package Jar') {
      steps {
        sh 'mvn -DskipTests package'
      }
    }

    stage('Build Docker Image') {
      steps {
        withCredentials([usernamePassword(
          credentialsId: 'dockerhub-creds',
          usernameVariable: 'DH_USER',
          passwordVariable: 'DH_TOKEN'
        )]) {
          sh '''
            export FULL_IMAGE="${DH_USER}/${IMAGE_NAME}"

            docker version
            docker build -t ${FULL_IMAGE}:${IMAGE_TAG} .
            docker tag ${FULL_IMAGE}:${IMAGE_TAG} ${FULL_IMAGE}:latest
          '''
        }
      }
    }

    stage('Push Docker Image') {
      steps {
        withCredentials([usernamePassword(
          credentialsId: 'dockerhub-creds',
          usernameVariable: 'DH_USER',
          passwordVariable: 'DH_TOKEN'
        )]) {
          sh '''
            export FULL_IMAGE="${DH_USER}/${IMAGE_NAME}"

            echo "$DH_TOKEN" | docker login -u "$DH_USER" --password-stdin
            docker push ${FULL_IMAGE}:${IMAGE_TAG}
            docker push ${FULL_IMAGE}:latest
            docker logout
          '''
        }
      }
    }
  }

  post {
    always {
      junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
      archiveArtifacts artifacts: 'target/site/jacoco/**', fingerprint: true
      archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
    }

    success {
      echo "✅ SUCCESS: ${env.BUILD_URL}"
      echo "✅ Image pushed with tag: ${IMAGE_TAG} and latest"
    }

    unstable {
      echo "⚠️ UNSTABLE (tests failed): ${env.BUILD_URL}"
    }

    failure {
      echo "❌ FAILURE: ${env.BUILD_URL}"
    }

    aborted {
      echo "⏹️ ABORTED (maybe approval timeout): ${env.BUILD_URL}"
    }

    cleanup {
      // Optional cleanup to save disk
      withCredentials([usernamePassword(
        credentialsId: 'dockerhub-creds',
        usernameVariable: 'DH_USER',
        passwordVariable: 'DH_TOKEN'
      )]) {
        sh '''
          export FULL_IMAGE="${DH_USER}/${IMAGE_NAME}"
          docker image rm -f ${FULL_IMAGE}:${IMAGE_TAG} ${FULL_IMAGE}:latest 2>/dev/null || true
        '''
      }
    }
  }
}
