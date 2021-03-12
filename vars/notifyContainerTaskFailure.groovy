// Notify of failure of a container task
void call(
    String environment,
    String segment,
    String cluster,
    String task,
    String container,
    String action,
    String channels
) {
    notifyFailure(
        "Container Task Failed",
        "Environment: ${environment}  \nSegment: ${segment}  \nCluster: ${cluster}  \nTask: ${task}  \nContainer: ${container}  \nAction: ${action}",
        channels
    )
}

