// Run a CI specific target - they always start with ci:
// Permit override of node options e.g. to increase heap size for
// large builds
def call(
    String target,
    String nodeOptions = '--max-old-space-size=4096'
) {
    script {
        env['run_target'] = "ci:${target}"
        env['run_target_node_options'] = "${nodeOptions}"
    }
    sh '''#!/bin/bash
        NODE_OPTIONS="${run_target_node_options}" npm run "${run_target}"
    '''
}
