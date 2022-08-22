// Set the node environment to the version specified
def call(
    String version,
    String scope = "global"
) {
    script {
        env['required_nodejs_version'] = version
        env['required_scope'] = scope
    }

    // The agent may already have the required version installed
    sh '''#!/bin/bash
        current_nodejs_version="$(nodenv version)"
        if [[ ! ("${current_nodejs_version}" =~ ${required_nodejs_version}) ]]; then
            nodenv update-version-defs
            nodenv install "${required_nodejs_version}" ||
                { nodenv install --list; exit 1; }
            case ${required_scope} in
              global|local)
                nodenv ${required_scope} "${required_nodejs_version}" || exit $?
                ;;
            esac
        fi
        nodenv version || exit $?
    '''
}
