// Notify of transfer of builds between environments
// Only needed where environments don't share registries
def call(
    String fromEnvironment,
    String environment,
    String segment,
    String group,
    String units,
    String commit,
    String channels
) {
    notifySuccess(
        "Transfer Completed",
        "FromEnvironment: ${fromEnvironment}  \nEnvironment: ${environment}  \nSegment: ${segment}  \nGroup: ${group}  \nUnits: ${units}  \nCommit: ${commit}",
        channels
    )
}

