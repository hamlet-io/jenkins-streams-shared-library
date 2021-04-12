// Load a product CMDB to get access to the stream pipelines
def call(
    String product_cmdb_url = '',
    String branch = 'master',
    String credential = 'github'
) {
    // Product Setup
    dir('.hamlet/product') {
        script {
            if (product_cmdb_url == '') {
                // CMDB is configured as part of the pipeline
                // Skip the default commit so we can force the value
                // of GIT_COMMIT in the environment when updating build
                // references.
                checkout scm
            } else {
                // Need to load CMDB - don't include it in changelog calculations
                git(
                    url: product_cmdb_url,
                    branch: branch,
                    credentialsId: credential,
                    changelog: false,
                    poll: false
                )
            }
        }
    }
}

