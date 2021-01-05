// Notify of failure of acceptance of an environment
void call(
    String environment,
    String segment,
    String group,
    String units,
    String release,
    String channels
) {
    notifyFailure(
        "Accept Failed",
        "Environment: ${environment}  \nSegment: ${segment}  \nGroup: ${group}  \nUnits: ${units}  \nRelease: ${release}",
        channels
    )
}
