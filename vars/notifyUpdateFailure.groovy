// Notify of failure of updates to build references
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
        "Update References Failed",
        "Environment: ${environment}  \nSegment: ${segment}  \nGroup: ${group}  \nUnits: ${units}  \nCommit: ${commit}  \nTag: ${tag}",
        channels
    )
}

