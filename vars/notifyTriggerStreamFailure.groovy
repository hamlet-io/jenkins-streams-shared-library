// Notify of failure to trigger a stream
void call(
    String stream,
    String segment,
    String group,
    String deploymentUnits,
    String codeUnits,
    String commit,
    String tag,
    String channels
) {
    notifyFailure(
        "Stream Trigger Failure",
        "Stream: ${stream}  \nSegment: ${segment}  \nDeployment Units: ${deploymentUnits}  \nCode Units: ${codeUnits}  \nCommit: ${commit}  \Tag: ${tag}",
        channels
    )
}

