// Trigger the transfer library job
def call(
    String jobBase,
    String fromEnvironment,
    String environment,
    String segment,
    String group,
    String codeUnits,
    String commit,
    String channels
) {
    script {
        build job: "${jobBase}/library/transfer",
            wait: true,
            parameters: [
                string(name: 'FROM_ENVIRONMENT', value: fromEnvironment),
                string(name: 'ENVIRONMENT', value: environment),
                string(name: 'SEGMENT', value: segment),
                string(name: 'DEPLOYMENT_GROUP', value: group),
                string(name: 'CODE_UNITS', value: codeUnits),
                string(name: 'GIT_COMMIT', value: commit),
                string(name: 'CHANNELS', value: channels)
            ]
    }
}
