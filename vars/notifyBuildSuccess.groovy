// Notify of success of code build
def call(
    String segment,
    String unit,
    String commit,
    String channels
) {
    notifyFailure(
        "Build Completed",
        "Segment: ${segment}  \nUnit: ${unit}  \nCommit: ${commit}",
        channels
    )
}
