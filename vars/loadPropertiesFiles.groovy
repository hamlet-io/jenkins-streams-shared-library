// Load a repo to get access to the properties files.
def call(
    String repo_url = '',
    String repo_branch = 'master',
    String credentialsId = 'github'
) {
    dir('.hamlet/properties') {
        script {
            if ( repo_url ) {
                // Need to load the repo - don't include it in changelog calculations
                git(
                    url: repo_url,
                    branch: repo_branch,
                    credentialsId: credentialsId,
                    changelog: false,
                    poll: false
                )
            } else {
                // repo is configured as part of the pipeline
                // Skip the default commit so we can force the value
                // of GIT_COMMIT in the environment when updating build
                // references.
                checkout scm
            }
        }
    }
}

