// Set the hamlet engine to use for
def call(
    String engine,
    String cliVersion = ''
) {
    script {

        env['REQUIRED_HAMLET_ENGINE'] = engine
        env['REQUIRED_HAMLET_CLI'] = cliVersion

        // Handle the common case of a specific CLI version
        if (cliVersion ==~ /^[0-9][0-9.]+$/ ) {
            env['REQUIRED_HAMLET_CLI'] = "==" + cliVersion
        }
    }

    // The agent may already have the required version installed
    sh '''#!/bin/bash

        echo "Updating the hamlet cli ${REQUIRED_HAMLET_CLI:+(${REQUIRED_HAMLET_CLI})} ..."
        pip install --quiet --upgrade "hamlet${REQUIRED_HAMLET_CLI}"
        echo "hamlet version = \"$(hamlet --version)\""

        echo "Updating the hamlet engine (${REQUIRED_HAMLET_ENGINE}) ..."
        hamlet engine install-engine --update "${REQUIRED_HAMLET_ENGINE}"
        hamlet engine set-engine "${REQUIRED_HAMLET_ENGINE}"
        echo "hamlet engine = \"$(hamlet engine get-engine)\""
    '''
}
