// Notify of entry of deployment into an environment
void call(
    String approver,
    String environment,
    String segment,
    String group,
    String deploymentUnits,
    String codeUnits,
    String commit,
    String tag,
    String release,
    String channels
) {
    notifySuccess(
        "Entry to environment confirmed",
        "Approver: ${approver}  \nEnvironment: ${environment}  \nSegment: ${segment}  \nGroup: ${group}  \nDeployment Units: ${deploymentUnits}  \nCode Units: ${codeUnits}  \nCommit: ${commit}  \nTag: ${tag}  \nRelease: ${release}",
        channels
    )
}
