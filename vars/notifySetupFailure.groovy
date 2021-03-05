// Notify of failure of build setup e.g. package manager update failure
def call(
    String unit,
    String commit,
    String channels
) {
    notifyFailure(
        "Setup Failed",
        "Unit: ${unit}  \nCommit: ${commit}",
        channels
    )
}

