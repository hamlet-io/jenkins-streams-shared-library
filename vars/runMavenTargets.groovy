// Run one or more MVN targets
// Note: they don't follow the ci: convention usd for npm
def call(
    String targets
) {
    script {
        env['run_targets'] = targets
    }
    sh '''#!/bin/bash
    . ${WORKSPACE}/tools/maven.sh
    for run_target in ${run_targets}; do
        mvn "${run_target}" || exit $?
    done
    '''
}
