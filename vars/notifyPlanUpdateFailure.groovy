// Notify of failure of update during plan preparation
void call(
    String environment,
    String segment,
    String group,
    String units,
    String commit,
    String tag,
    String channels
) {
    notifyFailure(
        "Plan Update Failed",
        "Environment: ${environment}  \nSegment: ${segment}  \nGroup: ${group}  \nUnits: ${units}  \nCommit: ${commit}  \nTag: ${tag}",
        channels
    )
}

