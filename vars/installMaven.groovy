// Set the maven environment to the version specified
def call(
    String version = '3.6.3'
    String checksum = 'c35a1803a6e70a126e80b2b3ae33eed961f83ed74d18fcd16909b2d44d7dada3203f1ffe726c17ef8dcca2dcaa9fca676987befeadc9b9f759967a8cb77181c0'
) {
    script {
        env['required_maven_version'] = version
        env['required_maven_checksum'] = checksum
    }

    sh '''#/bin/bash

        # Install Apache Maven for the build.

        # Ensure that the installation directory exists.
        INSTALL_DIR="${WORKSPACE}/tools"
        mkdir -p "${INSTALL_DIR}"
        cd "${INSTALL_DIR}"

        MVN_VERSION=${required_maven_version}
        MVN_TARBALL="apache-maven-${MVN_VERSION}-bin.tar.gz"
        MVN_INSTALL_URL="https://downloads.apache.org/maven/maven-3/${MVN_VERSION}/binaries/${MVN_TARBALL}"
        MVN_CHECKSUM=${required_maven_checksum}

        # Download the given version of Apache Maven Binary.
        WGET_OPTS="--verbose"
        wget ${WGET_OPTS} "${MVN_INSTALL_URL}"

        # Check its signature.
        CHECKSUM=`sha512sum "${MVN_TARBALL}" | cut -d\\  -f1`
        if [ "${MVN_CHECKSUM}" != "${CHECKSUM}" ] ; then
        echo "Invalid checksum for ${MVN_TARBALL}"
        echo "${MVN_CHECKSUM}"
        echo "${CHECKSUM}"
        exit 1
        fi

        # Unpack the tarball to the installation directory
        umask 0
        tar -xf "${MVN_TARBALL}"

        # Create an init script to initialize maven on login.
        rm -f ${WORKSPACE}/tools/maven.sh
        cat > ${WORKSPACE}/tools/maven.sh <<EOF
# **********************************************************************
# Apache Maven ${MVN_VERSION} Environment
#
#  Downloaded from https://downloads.apache.org/maven/maven-3/${MVN_VERSION}/binaries/apache-maven-${MVN_VERSION}-bin.tar.gz
#
# **********************************************************************

export    M2_HOME="${INSTALL_DIR}/apache-maven-${MVN_VERSION}"
export MAVEN_HOME="${INSTALL_DIR}/apache-maven-${MVN_VERSION}"
export       PATH="${INSTALL_DIR}/apache-maven-${MVN_VERSION}/bin:${PATH}"

EOF

        # Ensure that the init script is executable.
        chmod 755 ${WORKSPACE}/tools/maven.sh
        . ${WORKSPACE}/tools/maven.sh

    '''

}



