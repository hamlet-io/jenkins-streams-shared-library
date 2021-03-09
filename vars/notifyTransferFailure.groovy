// Notify of failure of transfer of builds between environments
def call(
    String fromEnvironment,
    String environment,
    String segment,
    String group,
    String deploymentUnits,
    String commit,
    String channels
) {
    notifyFailure(
        "Transfer Failed",
        "FromEnvironment: ${fromEnvironment}  \nEnvironment: ${environment}  \nSegment: ${segment}  \nGroup: ${group}  \nUnits: ${deploymentUnits}  \nCommit: ${commit}",
        channels
    )
}

