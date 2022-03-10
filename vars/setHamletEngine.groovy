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
    }

    // The agent may already have the required version installed
    sh '''#!/bin/bash

        pip install --upgrade "hamlet${hamlet_cli_version}"

        current_hamlet_engine="$(hamlet engine get-engine)"
        if [[ ! ("${current_hamlet_engine}" == ${required_hamlet_engine}) ]]; then
            hamlet engine install-engine "${required_hamlet_engine}"
        fi

        if [[ "${update_hamlet_engine}" == "true" ]]; then
            hamlet engine install-engine --update "${required_hamlet_engine}"
        fi

        hamlet engine set-engine "${required_hamlet_engine}"

        echo "hamlet version = \"$(hamlet --version)\""
        echo "hamlet engine = \"$(hamlet engine get-engine)\""
    '''
}
