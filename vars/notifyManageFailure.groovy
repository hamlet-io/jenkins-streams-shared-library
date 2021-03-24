// Notify of failure of manage segment
def call(
    String deploymentMode,
    String environment,
    String segment,
    String levels,
    String segmentUnits,
    String solutionUnits,
    String applicationUnits,
    String channels
) {
    notifyFailure(
        "Manage Environment Failed",
        "Mode: ${deploymentMode}  \nEnvironment: ${environment}  \nSegment: ${segment}  \nLevels: ${levels}  \nSegmentUnits: ${segmentUnits}  \nSolutionUnits: ${solutionUnits}  \nApplicationUnits: ${applicationUnits}",
        channels
    )
}

