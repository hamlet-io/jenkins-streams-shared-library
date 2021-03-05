// Build openapi spec
def call(
    String image_formats
) {
    sh '''#!/bin/bash
        ${AUTOMATION_BASE_DIR}/setContext.sh
    '''

    script {
        def contextProperties = readProperties interpolate: true, file: "${WORKSPACE}/context.properties";
        contextProperties.each{ k, v -> env["${k}"] ="${v}" }
    }

    // Needed by buildOpenapi.sh and originally set in buildSetup.sh
    script {
        env['IMAGE_FORMATS_LIST'] = image_formats
    }

    sh '''#!/bin/bash
        ${AUTOMATION_DIR}/buildOpenapi.sh
     '''
}
