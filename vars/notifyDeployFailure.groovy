// Notify of failure of a deployment to an environment
void call(
    String environment,
    String segment,
    String group,
    String deploymentUnits,
    String release,
    String channels
) {
    notifyFailure(
        "Deploy Failed",
        "Environment: ${environment}  \nSegment: ${segment}  \nGroup: ${group}  \nUnits: ${deploymentUnits}  \nRelease: ${release}",
        channels
    )
}

