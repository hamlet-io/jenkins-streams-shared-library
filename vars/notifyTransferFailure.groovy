// Notify of failure of transfer of builds between environments
def call(
    String fromEnvironment,
    String environment,
    String segment,
    String group,
    String units,
    String commit,
    String channels
) {
    notifyFailure(
        "Transfer Failed",
        "FromEnvironment: ${fromEnvironment}  \nEnvironment: ${environment}  \nSegment: ${segment}  \nGroup: ${group}  \nUnits: ${units}  \nCommit: ${commit}",
        channels
    )
}

