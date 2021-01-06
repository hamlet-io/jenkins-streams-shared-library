// Load properties defined in the CMDB into the environment
def call(
    String properties_file,
    String noWorkspace = 'false'
) {
    script {
        if (noWorkspace == 'true') {
            // Assume CMDB configured on the job
            def fileContent = readTrusted path: "pipelines/properties/${properties_file}.properties"
            def contextProperties = readProperties interpolate: true, text: fileContent
            contextProperties.each{ k, v -> env["${k}"] ="${v}" }
        } else {
            // CMDB is local in the workspace
            dir('.hamlet/product') {
                // Load in the properties file from the cmdb
                def contextProperties = readProperties interpolate: true, file: "pipelines/properties/${properties_file}.properties";
                contextProperties.each{ k, v -> env["${k}"] ="${v}" }
            }
        }
    }
}
