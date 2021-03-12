// Trigger the deploy library job
def call(
    String jobBase,
    String environment,
    String segment,
    String clusterTier,
    String clusterComponent,
    String taskId,
    String taskInstance,
    String taskVersion,
    String taskContainer,
    String action,
    String channels
) {
    script {
        build job: "${jobBase}/library/containertask",
            wait: true,
            parameters: [
                string(name: 'ENVIRONMENT', value: environment),
                string(name: 'SEGMENT', value: segment),
                string(name: 'CLUSTER_TIER', value: clusterTier),
                string(name: 'CLUSTER_COMPONENT', value: clusterComponent),
                string(name: 'TASK_ID', value: taskId),
                string(name: 'TASK_INSTANCE', value: taskInstance),
                string(name: 'TASK_VERSION', value: taskVersion),
                string(name: 'TASK_CONTAINER', value: taskContainer),
                string(name: 'ACTION', value: action),
                string(name: 'CHANNELS', value: channels)
            ]
    }
}
