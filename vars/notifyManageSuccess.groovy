// Notify of success of manage segment
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
    notifySuccess(
        "Manage Environment Completed",
        "Mode: ${deploymentMode}  \nEnvironment: ${environment}  \nSegment: ${segment}  \nLevels: ${levels}  \nSegment Units: ${segmentUnits}  \nSolution Units: ${solutionUnits}  \nApplication Units: ${applicationUnits}",
        channels
    )
}

