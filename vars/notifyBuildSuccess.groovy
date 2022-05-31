// Notify of success of code build
def call(
    String segment,
    String deploymentUnits,
    String commit,
    String channels
) {
    notifySuccess(
        "Build Completed",
        "Segment: ${segment}  \nUnit(s): ${deploymentUnits}  \nCommit: ${commit}",
        channels
    )
}
