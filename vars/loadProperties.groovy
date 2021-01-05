// Load properties defined in the CMDB into the environment
def call(
    String properties_file
) {
    // Product Setup
    dir('.hamlet/product') {
         // Load in the properties file from the cmdb
        script {
            def contextProperties = readProperties interpolate: true, file: "pipelines/properties/${properties_file}.properties";
            contextProperties.each{ k, v -> env["${k}"] ="${v}" }
        }
    }
}
