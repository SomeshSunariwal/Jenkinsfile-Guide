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

## 5. Parameters

```groovy
parameters {
    string(name: 'PERSON', defaultValue: '', description: '')
    text(name: 'BIOGRAPHY', defaultValue: '', description: '')
    booleanParam(name: 'TOGGLE', defaultValue: true, description: '')
    choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: '')
    password(name: 'PASSWORD', defaultValue: '', description: '')
}
stages {
    stage {
        step {
            echo "${params.PERSON}"
        }
    }
}
```

## 6. User Input In build

its a script so that is why we have to handle it inside the script that uses groovy

```groovy
steps{
    script {
        InputValue = input(
            id: 'userInput',
            message: 'Want Second Step?',
            parameters: [
                choice(name: 'CHOICE', choices: ['true', 'false'], description: 'Enter your Choice'),
            ]
        )
    }
}
```

# Issues and Solutions

### 1. Got permission denied while trying to connect to the Docker daemon socket at unix:///var/run/docker.sock: Post some-link : dial unix /var/run/docker.sock: connect: permission denied

`solution : ` change the permission of docker.sock

```bash
sudo chmod 777 /var/run/docker.sock
```

# Resource

https://www.jenkins.io/doc/book/pipeline/syntax/
