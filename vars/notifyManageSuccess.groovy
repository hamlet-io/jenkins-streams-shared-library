// Notify of success of manage segment
def call(
    String environment,
    String segment,
    String levels,
    String segmentUnits,
    String solutionUnits,
    String applicationUnits,
    String channels
) {
    notifySuccess(
        "Manage Environment Completed",
        "Environment: ${environment}  \nSegment: ${segment}  \nLevels: ${levels}  \nSegmentUnits: ${segmentUnits}  \nSolutionUnits: ${solutionUnits}  \nApplicationUnits: ${applicationUnits}",
        channels
    )
}

