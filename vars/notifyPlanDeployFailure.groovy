// Notify of failure of deploy during plan preparation
void call(
    String environment,
    String segment,
    String group,
    String deploymentUnits,
    String channels
) {
    notifyFailure(
        "Plan Deploy Failed",
        "Environment: ${environment}  \nSegment: ${segment}  \nGroup: ${group}  \nUnits: ${deploymentUnits}",
        channels
    )
}

