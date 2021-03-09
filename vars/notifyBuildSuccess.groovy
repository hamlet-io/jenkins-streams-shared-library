// Notify of success of code build
def call(
    String segment,
    String deploymentUnit,
    String commit,
    String channels
) {
    notifyFailure(
        "Build Completed",
        "Segment: ${segment}  \nUnit: ${deploymentUnit}  \nCommit: ${commit}",
        channels
    )
}
