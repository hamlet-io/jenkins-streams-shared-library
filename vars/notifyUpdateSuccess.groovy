// Notify of updates to build references
void call(
    String environment,
    String segment,
    String group,
    String codeUnits,
    String commit,
    String tag,
    String channels
) {
    notifySuccess(
        "Update References Completed",
        "Environment: ${environment}  \nSegment: ${segment}  \nGroup: ${group}  \nUnits: ${codeUnits}  \nCommit: ${commit}  \nTag: ${tag}",
        channels
    )
}

