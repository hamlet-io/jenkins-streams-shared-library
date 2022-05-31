// Notify of failure of code build
def call(
    String segment,
    String deploymentUnits,
    String commit,
    String channels
) {
    notifyFailure(
        "Build Failed",
        "Segment: ${segment}  \nUnit(s): ${deploymentUnits}  \nCommit: ${commit}",
        channels
    )
}

