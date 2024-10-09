pipeline {
    agent any

    stages {
        stage('A') {
            steps {
                echo 'Stage A: Build'
            }
        }

        stage('B') {
            steps {
                echo 'Stage B: Test'
            }
        }

        stage('C') {
            steps {
                echo 'Stage C: Pre-Deployment'
            }
        }

        stage('Approval 1') {
            steps {
                script {
                    // First approval: only 'Test1' can approve
                    def approver1 = input(
                        message: 'Approval Required: First Approver',
                        submitter: 'Test1'
                    )
                    echo "First approver: ${approver1}"
                    env.FIRST_APPROVER = approver1
                }
            }
        }

        stage('Approval 2') {
            steps {
                script {
                    // Second approval: only 'Test2' can approve
                    def approver2 = input(
                        message: 'Approval Required: Second Approver',
                        submitter: 'Test2'
                    )
                    echo "Second approver: ${approver2}"

                    // Check if the second approver is the same as the first
                    if (approver2 == env.FIRST_APPROVER) {
                        error 'Deployment Failed: The same user cannot approve both steps!'  // Fail the deployment
                    }
                }
            }
        }

        stage('D') {
            steps {
                echo 'Stage D: Deployment'
            }
        }
    }
}