// Trigger the update library job
def call(
    String jobBase,
    String environment,
    String segment,
    String group,
    String codeUnits,
    String commit,
    String tag,
    String channels
) {
    script {
        build job: "${jobBase}/library/update",
            wait: true,
            parameters: [
                string(name: 'ENVIRONMENT', value: environment),
                string(name: 'SEGMENT', value: segment),
                string(name: 'DEPLOYMENT_GROUP', value: group),
                string(name: 'CODE_UNITS', value: codeUnits),
                string(name: 'GIT_COMMIT', value: commit),
                string(name: 'CODE_TAG', value: tag),
                string(name: 'CHANNELS', value: channels)
            ]
    }
}
