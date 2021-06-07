// Set the python environment to the version specified
def call(
    String version
) {
    script {
        env['required_python_version'] = version
    }

    // The agent may already have the required version installed
    sh '''#!/bin/bash
        current_python_version="$(nodenv version)"
        if [[ ! ("${current_python_version}" =~ ${required_python_version}) ]]; then
            pyenv install "${required_python_version}" ||
                { pyenv install --list; exit 1; }
            pyenv global  "${required_python_version}" || exit $?
            pip install --upgrade awscli pip virtualenv || exit $?
        fi
        pyenv version || exit $?
        pip install --upgrade awscli pip virtualenv || exit $?
    '''
}
