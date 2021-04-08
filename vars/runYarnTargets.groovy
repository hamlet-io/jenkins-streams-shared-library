// Run one or more Yarn CI specific targets - they always start with ci:
def call(
    String targets
) {
    script {
        env['run_targets'] = targets
    }
    sh '''#!/bin/bash
    for run_target in ${run_targets}; do
        yarn run "ci:${run_target,,}"
    done
    '''
}
