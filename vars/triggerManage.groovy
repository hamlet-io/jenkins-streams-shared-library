// Trigger the manage library job
def call(
    String jobBase,
    String deploymentMode,
    String environment,
    String segment,
    String levels,
    String segmentUnits,
    String solutionUnits,
    String applicationUnits,
    String treatAsSignificant,
    String channels
) {
    script {
        build job: "${jobBase}/library/manage",
            wait: true,
            parameters: [
                string(name: 'DEPLOYMENT_MODE', value: deploymentMode),
                string(name: 'ENVIRONMENT', value: environment),
                string(name: 'SEGMENT', value: segment),
                string(name: 'LEVELS_LIST', value: levels),
                string(name: 'SEGMENT_UNITS_LIST', value: segmentUnits),
                string(name: 'SOLUTION_UNITS_LIST', value: solutionUnits),
                string(name: 'APPLICATION_UNITS_LIST', value: applicationUnits),
                booleanParam(name: 'TREAT_RUN_ID_DIFFERENCES_AS_SIGNIFICANT', value: treatAsSignificant.toBoolean() ),
                string(name: 'CHANNELS', value: channels)
            ]
    }
}
