// Notify of failure of build setup e.g. package manager update failure
def call(
    String deploymentUnits,
    String commit,
    String channels
) {
    notifyFailure(
        "Setup Failed",
        "Unit(s): ${deploymentUnits}  \nCommit: ${commit}",
        channels
    )
}

