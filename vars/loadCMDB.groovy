// Load a product repo to get access to the stream pipelines
// Optionally load the accounts repo to permit hamlet based
// deployments.
def call(
    String product_cmdb_url,
    String product_cmdb_branch = 'master',
    String credentialsId = 'github',
    String accounts_cmdb_url = '',
    String accounts_cmdb_branch = 'master',
    String product_path = 'product',
    String accounts_path = 'accounts',
    String cmdb_path = "zzhamlet/cmdb",
    String cmdb_root_env_name = 'ROOT_DIR'
) {
    dir( "${cmdb_path}" ) {

        // Always load product repo as a minimum
        // It can contain accounts info as well
        dir( "${product_path}" ) {
            script {
                // Don't include it in changelog calculations
                git(
                    url: product_cmdb_url,
                    branch: product_cmdb_branch,
                    credentialsId: credentialsId,
                    changelog: false,
                    poll: false
                )
            }
        }

        // Accounts Setup
        dir( "${accounts_path}" ) {
            script {
                if ( accounts_cmdb_url ) {
                    // Need to load accounts repo - don't include it in changelog calculations
                    git(
                        url: accounts_cmdb_url,
                        branch: accounts_cmdb_branch,
                        credentialsId: credentialsId,
                        changelog: false,
                        poll: false
                    )
                }
            }
        }

        // Remember where the CMDB has been constructed
        script {
            env["${cmdb_root_env_name}"] = sh(script: 'pwd', returnStdout: true).trim()
        }
    }
}

