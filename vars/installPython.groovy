// Set the python environment to the version specified
def call(
    String version,
    String scope = "global"
) {
    script {
        env['required_python_version'] = version
        env['required_scope'] = scope
    }

    // The agent may already have the required version installed
    sh '''#!/bin/bash
        current_python_version="$(pyenv version)"
        if [[ ! ("${current_python_version}" =~ ${required_python_version}) ]]; then
            pyenv install "${required_python_version}" ||
                { pyenv install --list; exit 1; }
            case ${required_scope} in
              global|local)
                pyenv ${required_scope} "${required_python_version}" || exit $?
                ;;
            esac
        fi
        pyenv version || exit $?
        pip install --upgrade awscli pip virtualenv hamlet || exit $?
    '''
}
