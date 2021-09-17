// Send notifications to whatever channels are required. Currently slack and Teams are supported
def call(
    String title,
    String message,
    String channels,
    String colour
) {
    script {
        def channelsList = channels.split(",")

        channelsList.each {
            def channel = it.trim()
            if (channel.startsWith("#")) {
                slackSend (
                    message: "*${title}* | ${BUILD_DISPLAY_NAME} (<${BUILD_URL}|${JOB_NAME}>)  \n${message}",
                    channel: channel,
                    color: colour
                )
            }

            if (channel.indexOf("office.com") != -1) {
                // Standard Office 365 format includes JOB NAME and BUILD_URL
                office365ConnectorSend (
                    message: "**${title}** | ${BUILD_DISPLAY_NAME}  \n${message}",
                    webhookUrl: channel,
                    color: colour
                )
            }
        }
    }
}
