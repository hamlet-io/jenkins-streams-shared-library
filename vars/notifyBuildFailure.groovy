// Notify of failure of code build
def call(
    String segment,
    String deploymentUnit,
    String commit,
    String channels
) {
    notifyFailure(
        "Build Failed",
        "Segment: ${segment}  \nUnit: ${deploymentUnit}  \nCommit: ${commit}",
        channels
    )
}

