// Upload artifacts to the format specific registries
def call(
    String deployment_unit,
    String scope,
    String image_formats,
    String git_commit,
    String image_paths = "",
) {
    // Inject parameters into the environment for the script steps
    // Variable names are treated as case insensitive so rename to avoid overwriting existing
    // environment variables
    script {
        env['upload_deployment_unit'] = deployment_unit
        env['upload_scope'] = scope
        env['upload_image_formats'] = image_formats
        env['upload_git_commit'] = git_commit
        env['upload_image_paths'] = image_paths
    }

    sh '''#!/bin/bash
    ${AUTOMATION_BASE_DIR}/setContext.sh || exit $?
    '''

    script {
        def contextProperties = readProperties interpolate: true, file: "${WORKSPACE}/context.properties";
        contextProperties.each{ k, v -> env["${k}"] ="${v}" }
    }

    sh '''#!/bin/bash
        for i in "${!upload_image_formats[@]}"; do
            image_args=()

            if [[ -n "${upload_image_paths[i]}" ]]; then
                image_args+=("-i" "${upload_image_paths[i]}")
            fi

            ${AUTOMATION_DIR}/manageImages.sh -u "${upload_deployment_unit}" -c "${upload_scope}" -f "${upload_image_formats[i],,}" -g "${upload_git_commit}" "${image_args[@]} || exit $?
        done
    '''
}
