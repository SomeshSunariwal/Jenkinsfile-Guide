# Jenkinsfile-Guide

Jenkins-Guide

## 1. Starter Template

```JenkinsFile
pipeline {
    agent any
    stages {
        stage ("build") {
            steps {
                echo "I will run"
            }
        }
    }
}
```

## 2. Post Operation

```
post {
    always {
        echo "I will always run"
    }
    success {
        echo "Only run when above successful"
    }
    failure {
        echo "only run when above failed"
    }
}
```

## 3. When Condition

```
stage('stage'){
    // only run wen condition fulfilled
    when {
        expression {
            BRANCH == 'dev' && NAME == 'Test'
        }
    }
    steps{
        echo 'Test Done'
    }
}
```
