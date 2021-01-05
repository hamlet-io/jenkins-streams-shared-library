// Trigger the deploy library job
def call(
    String jobBase,
    String environment,
    String segment,
    String group,
    String deploymentUnits,
    String release,
    String channels
) {
    script {
        build job: "${jobBase}/library/deploy",
            wait: true,
            parameters: [
                string(name: 'ENVIRONMENT', value: environment),
                string(name: 'SEGMENT', value: segment),
                string(name: 'DEPLOYMENT_GROUP', value: group),
                string(name: 'DEPLOYMENT_UNITS', value: deploymentUnits),
                string(name: 'RELEASE_IDENTIFIER', value: release),
                string(name: 'CHANNELS', value: channels)
            ]
    }

}
