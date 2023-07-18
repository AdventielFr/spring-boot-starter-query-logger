@Library('common-library-jenkinsfile@master') _

pipeline {

    agent {
        docker {
            label 'slave01'
            image 'maven:3.9.3-eclipse-temurin-17-alpine'
            registryUrl 'https://index.docker.io/v1/'
        }
    }

    environment {
        SSH_AGENT = 'gitlab-ssh'
        REGISTRY_HOST = "docker-registry.adventiel.com"
        BUILD_DATE = sh(returnStdout: true, script: "date -u +'%Y-%m-%dT%H:%M:%SZ'").trim()

        COMPONENT = "spring-boot-starter-query-logger"
    }

    options {
        ansiColor('xterm')
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
    }

    parameters {
        booleanParam(defaultValue: false, description: 'Release ?', name: 'RELEASE')
    }

    triggers { pollSCM('H/5 * * * *') }

    stages {

        stage('Package') {
            steps {
                mavenCmd("clean")
                mavenCmd('-U verify')
            }
        }

        stage('Upload to Nexus') {
            steps {
                mavenCmd("deploy -Dskip.unit.tests -Dskip.integration.tests")
            }
        }

        stage('Release') {
            when { expression { params.RELEASE } }
            steps {
                sshagent(["${env.SSH_AGENT}"]) {
                    mavenCmd('--batch-mode -Dsurefire.useFile=false -Dresume=false -Dmaven.test.skip=true -Darguments="-Dskip.unit.tests -Dskip.integration.tests" release:prepare release:perform')
                }
            }
        }
    }

    post {
        always {
            notifyBuild()
        }
    }
}
