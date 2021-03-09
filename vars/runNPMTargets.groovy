// Run one or more NPM CI specific targets - they always start with ci:
def call(
    String targets
) {
    script {
        env['run_targets'] = targets
    }
    sh '''#!/bin/bash
    for run_target in ${run_targets}; do
        npm run "ci:${run_target,,}"
    done
    '''
}
