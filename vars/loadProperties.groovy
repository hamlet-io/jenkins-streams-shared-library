// Load properties from a file
def call(
    String properties_file,
    String noWorkspace = 'false',
    String properties_path = 'pipelines/properties/',
    String properties_extension = '.properties'
) {
    script {
        if (properties_file) {
            if (noWorkspace.toBoolean()) {
                // Properties must be configured on the job
                def fileContent = readTrusted path: "${properties_path}${properties_file}${properties_extension}"
                def contextProperties = readProperties interpolate: true, text: fileContent
                contextProperties.each{ k, v -> env["${k}"] ="${v}" }
            } else {
                // Properties local to the workspace
                dir("zzhamlet/properties") {
                    // Load in the properties file
                    def contextProperties = readProperties interpolate: true, file: "${properties_path}${properties_file}${properties_extension}";
                    contextProperties.each{ k, v -> env["${k}"] ="${v}" }
                }
            }
        }
    }
}
