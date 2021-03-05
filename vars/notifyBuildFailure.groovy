// Notify of failure of code build
def call(
    String segment,
    String unit,
    String commit,
    String channels
) {
    notifyFailure(
        "Build Failed",
        "Segment: ${segment}  \nUnit: ${unit}  \nCommit: ${commit}",
        channels
    )
}

