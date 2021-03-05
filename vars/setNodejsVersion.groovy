// Set the node environment to the version specified
def call(
    String version
) {
    script {
        env['required_nodejs_version'] = version
    }

    // The agent may already have the required version installed
    sh '''#!/bin/bash
    current_nodejs_version="$(nodenv version)"
    if [[ ! ("${current_nodejs_version}" =~ ${required_nodejs_version}) ]]; then
        nodenv install "${required_nodejs_version}" ||
            { nodenv install --list; exit 1; }
        nodenv global  "${required_nodejs_version}" || exit $?
    fi
    nodenv version || exit $?
    '''
}
