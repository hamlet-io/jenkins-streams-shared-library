// Notify of failure of QA of build
def call(
    String deploymentUnits,
    String commit,
    String channels
) {
    notifyFailure(
        "QA Failed",
        "Unit(s): ${deploymentUnits}  \nCommit: ${commit}",
        channels
    )
}

