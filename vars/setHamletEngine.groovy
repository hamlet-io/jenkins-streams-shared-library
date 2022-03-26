// Set the hamlet engine to use for
def call(
    String engine,
    Boolean update = false,
    String cliVersion = ''
) {
    script {

        env['required_hamlet_engine'] = engine
        env['update_hamlet_engine'] = update
        env['hamlet_cli_version'] = cliVersion

        // Handle the common case of a specific CLI version
        if (cliVersion ==~ /^[0-9][0-9.]+$/ ) {
            env['hamlet_cli_version'] = "==" + cliVersion
        }
    }

    // The agent may already have the required version installed
    sh '''#!/bin/bash

        echo "Updating the hamlet cli ..."
        pip install --quiet --upgrade "hamlet${hamlet_cli_version}"
        echo "hamlet version = \"$(hamlet --version)\""

        echo "Updating the hamlet engine to ${required_hamlet_engine} ..."
        hamlet engine install-engine --update "${required_hamlet_engine}"
        hamlet engine set-engine "${required_hamlet_engine}"
        echo "hamlet engine = \"$(hamlet engine get-engine)\""
    '''
}
