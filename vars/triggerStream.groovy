// Trigger a stream
def call(
    String jobBase,
    String stream,
    String segment,
    String group,
    String deploymentUnits,
    String codeUnits,
    String commit,
    String tag = ''
) {
    script {
        build job: "${jobBase}/streams/${stream}",
            wait: false,
            parameters: [
                string(name: 'SEGMENT', value: segment),
                string(name: 'DEPLOYMENT_GROUP', value: group),
                string(name: 'DEPLOYMENT_UNITS', value: deploymentUnits),
                string(name: 'CODE_UNITS', value: codeUnits),
                string(name: 'GIT_COMMIT', value: commit)
                string(name: 'CODE_TAG', value: tag),
                string(name: 'RELEASE_IDENTIFIER', value: tag)
            ]
    }
}
