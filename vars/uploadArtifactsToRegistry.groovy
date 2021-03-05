// Upload artifacts to the format specific registries
def call(
    String deployment_unit,
    String scope,
    String image_formats,
    String git_commit
) {
    // Inject parameters into the environment for the script steps
    // Variable names are treated as case insensitive so rename to avoid overwriting existing
    // environment variables
    script {
        env['upload_deployment_unit'] = deployment_unit
        env['upload_scope'] = scope
        env['upload_image_formats'] = image_formats
        env['upload_git_commit'] = git_commit
    }

    sh '''#!/bin/bash
    ${AUTOMATION_BASE_DIR}/setContext.sh || exit $?
    '''

    script {
        def contextProperties = readProperties interpolate: true, file: "${WORKSPACE}/context.properties";
        contextProperties.each{ k, v -> env["${k}"] ="${v}" }
    }

    sh '''#!/bin/bash
        for upload_image_format in ${upload_image_formats}; do
            ${AUTOMATION_DIR}/manageImages.sh -u "${upload_deployment_unit}" -c "${upload_scope}" -f "${upload_image_formats,,}" -g "${upload_git_commit}" || exit $?
        done
    '''
}
