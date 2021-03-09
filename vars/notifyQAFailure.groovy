// Notify of failure of QA of build
def call(
    String deploymentUnit,
    String commit,
    String channels
) {
    notifyFailure(
        "QA Failed",
        "Unit: ${deploymentUnit}  \nCommit: ${commit}",
        channels
    )
}

