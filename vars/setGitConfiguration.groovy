// Set required git configuration settings
def call(
    String email,
    String name
) {
    script {
        env['set_git_user_email'] = email
        env['set_git_user_name'] = 'automation'

        if (user) {
            env['set_git_user_name'] = name
        }
    }
    sh '''#!/bin/bash
        git config --global user.name "${set_git_user_name}"
        git config --global user.email "${set_git_user_email}"
    '''
}
