// Notify of failure of QA of build
def call(
    String unit,
    String commit,
    String channels
) {
    notifyFailure(
        "QA Failed",
        "Unit: ${unit}  \nCommit: ${commit}",
        channels
    )
}

