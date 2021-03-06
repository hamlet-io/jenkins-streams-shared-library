// Notify of deployment to an environment
void call(
    String environment,
    String segment,
    String group,
    String deploymentUnits,
    String release,
    String channels
) {
    notifySuccess(
        "Deploy Completed",
        "Environment: ${environment}  \nSegment: ${segment}  \nGroup: ${group}  \nUnits: ${deploymentUnits}  \nRelease: ${release}",
        channels
    )
}

