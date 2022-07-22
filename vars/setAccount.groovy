// Determine the ACCOUNT holding the product infrastructure
def call(
    String product,
    String environment,
    String segment,
    String accountVar = 'ACCOUNT'
) {
    script {
        // Check for segment specific account setting
        if ( env[(product + '_' + environment + '_' + segment + '_ACCOUNT').toUpperCase()] ) {
            env[accountVar] = env[(product + '_' + environment + '_' + segment + '_ACCOUNT').toUpperCase()]
        }
        else {
            // Check for environment specific account setting
            if ( env[(product + '_' + environment + '_ACCOUNT').toUpperCase()] ) {
                env[accountVar] = env[(product + '_' + environment + '_ACCOUNT').toUpperCase()]
            }
            else {
                // Fallback to product level account settings
                if ( env[(product + '_ACCOUNT').toUpperCase()] ) {
                    env[accountVar] = env[(product + '_ACCOUNT').toUpperCase()]
                }
            }
        }
    }
}

