// Notify of failure of build setup e.g. package manager update failure
def call(
    String deploymentUnit,
    String commit,
    String channels
) {
    notifyFailure(
        "Setup Failed",
        "Unit: ${deploymentUnit}  \nCommit: ${commit}",
        channels
    )
}

