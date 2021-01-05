# jenkins-streams-shared-library

Support for the "streams" convention to integrate hamlet with Jenkinsfiles

The basic idea is that build pipelines know how to build things and what deployment "stream" to inject the build results into depending on the build trigger condition. However builds know nothing of environments as such.

Stream pipelines then define the deployment journey of builds through environments. Each environment can have an entry and exit "gate", which define the actions needed for builds to be deployed into an environment (entry) and for the builds to be considered suitable to proceed to the next environment (exit). Depending on the conditions included in the gates, a variety of deplyoment patterns, including continuous deployment and managed releases can be implemented.

Streams are designed to work with agent none, meaning they run on the lightweight executor and thus don't consume resources while waiting for user input - a typical condition used in a gate. They thus rely on triggering other jobs to do the actual work of the deployment. This library includes a collection of pipeline descriptions designed for this task.

The stream convention provides a nice separation between the CI part of the world controlled via a Jenkinsfile in a code repo, and the CD part of the world controlled by a Jenkinsfile kept in the hamlet CMDB repo. Developers are in full control of what happens in their builds, while the change management folks worry about the desired processes to get the code into production.

This file provides a range of support routines to assist in the implementation of the streams convention.


