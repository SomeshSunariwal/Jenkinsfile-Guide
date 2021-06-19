# Jenkinsfile-Guide

Jenkins-Guide

## 1. Starter Template

```groovy
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

```groovy
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

```groovy
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

## 4. Environment Variables

Jenkins provide by default env variables that can be seen here :
http://localhost:8080/env-vars.html

### Setting up own env variables

```groovy
pipeline {
    agent any

    environment {
        version = '1.0'
    }
    stages {
        stage ("build") {
            steps {
                echo "version : ${version}"
            }
        }
    }
}
```

### Use Jenkins Credentials

```groovy
withCredentials([
        usernamePassword(credentialsId: 'default-test-id', usernameVariable: 'USER', passwordVariable: 'PWD')
    ]) {
        // Never echo User Name Password
        // Only for Test
        echo "userName : $USER, Password: $PWD"
}
```
