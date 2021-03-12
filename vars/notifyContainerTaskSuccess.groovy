// Notify of successful completion of a container task
void call(
    String environment,
    String segment,
    String cluster,
    String task,
    String container,
    String action,
    String channels
) {
    notifySuccess(
        "Container Task Completed",
        "Environment: ${environment}  \nSegment: ${segment}  \nCluster: ${cluster}  \nTask: ${task}  \nContainer: ${container}  \nAction: ${action}",
        channels
    )
}

