// Set up credentials for cloud provider access
// The preferred model is that the agent runs under a role
// that can switch to the required account, in which can
// no specific configuration is necessary. Hamlet expects
// the user to be set to "ROLE" in this case.
// However, it is also possible to provide explicit credentials
// which need to be set based on the setting for the
def call(
    String credentialsId,
    String automationUserEnvVar = "HAMLET_AWS_AUTH_USER"
) {
    script {
        if (credentialsId) {
            if (! env[automationUserEnvVar] ) {
                // Set a default user
                env[automationUserEnvVar] = 'HAMLET'
            }

            def automationUser = env[automationUserEnvVar]

            // Product Setup
            withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'ACCESS_KEY', passwordVariable: 'SECRET_KEY' )]) {
                env["${automationUser}_AWS_ACCESS_KEY_ID"] = "${ACCESS_KEY}"
                env["${automationUser}_AWS_SECRET_ACCESS_KEY"] = "${SECRET_KEY}"
            }
        }
    }
}

