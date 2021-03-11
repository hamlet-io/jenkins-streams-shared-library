# jenkins-streams-shared-library

This repo provides a range of support steps and examples to assist in the use of Jenkinsfiles to implement a deployment convention called "streams".

## Repository structure

`vars` - a range of steps to streamline the implementation of streams for a product. This follows the conventions outlined in the [Jenkins documentation](https://www.jenkins.io/doc/book/pipeline/shared-libraries/)

`examples` - sample Jenkins jobs implementing the streams concept for the hamletDeploy deployment frameworks.

`assets` - images for use in this documentation

## Concept

The streams convention identifies three uses of Jenkins pipeline based jobs - build, stream, and library. As shown below;

![Overview](assets/overview.png)

### Build Jobs

Build jobs know how to build things and package them into artifacts, in line with the "build once, deploy many" philosophy. They store the artifacts in registries, where they can be then be sourced by downstream deployment processes.

Following successful build/test activities, build jobs know what "stream" to inject the build results into depending on the build trigger condition. However builds know nothing of environments as such. Triggers can be events such as changes to files as a result of a commit, addition of tags or scheduled builds. It is up to the build job designer to decide how many things will be built when each condition occurs, with this collection of changes then being passed to one or more streams for subsequent deployment activity.

Build jobs use the Jenkins multi-branch pipeline plugin to permit a variety of development practices including the use of pull requests and tagging.

### Stream jobs

Stream jobs define the deployment journey of the results of a build job through environments. In the overview diagram, two examples are shown. `cd` is the continuous stream which automatically deploys any changes to `ENV1`. `release` is a managed stream, with changes progressing through `ENV2`, `ENV3` and `PROD`. The transition from `ENV3` to `PROD` involves different accounts/subscriptions from the cloud provider.

Each environment within a stream logically has an entry and exit "gate", which define the actions needed for builds to be deployed into an environment (entry) and for the builds to be considered suitable to proceed to the next environment (exit). Depending on the conditions included in the gates, a variety of deployment patterns, including continuous deployment and managed releases, can be implemented. Typically entry is controlled by release managers scheduling the introduction of new code into environments, while test managers control exit gates. For simpler situations, exit gates can be omitted. In the release stream example, the gates are implemented as manual entry and exit confirmations.

Streams use the standard pipeline plugin and are designed to work with `agent none`, meaning they run on the lightweight executor and thus don't consume resources while waiting for user input - a typical condition used in a gate. They thus rely on triggering other "library" jobs to do the actual work of the deployment. They represent the desired release process with the concrete implementation being deferred to library jobs.

The streams convention provides a nice separation between the CI part of the world controlled via a Jenkinsfile in a code repo, and the CD part of the world controlled by a Jenkinsfile kept in the hamlet "Infrastructure As Code" (IaC) repo. Developers are in full control of what happens in their builds, while the change management folks worry about the desired processes to get the code through environments into production.

### Library Jobs

Library jobs use the standard pipeline plugin and are where the actions needed by a stream are implemented. They result in changes to cloud deployments and update to the IaC stores used to manage the desired and actual states of environments. Each library job provides a reusable building block for stream designers.

Library jobs are the point at which a specific IaC framework must be selected. Currently this repo contains [examples](examples) for the hamletDeploy framework. By isolating details of how to invoke a particular IaC framework, stream designers can focus on processes without needing to understand the intricate details of the specific framework used. In the examples provided, The IaC store contains the hamletDeploy CMDB.

### Jenkins job hierarchy

All jobs for a product are assumed to live under a "jobBase" path within the Jenkins job hierarchy. Build and stream jobs can determine how they wish to identify this point, with the provided examples assuming each product has its own top level `partition` folder in Jenkins.

Stream jobs are then expected to live under a `streams` folder, while library block jobs live under a `library` folder.

While no constraint is placed on the location of build jobs and the management jobs within Jenkins, by convention they live under the `build` and `manage` folders respectively. A further useful convention is for each code repository to have a job (if one Jenkinsfile in the code repository) or a folder (if more than one Jenkinsfile) under the `build` folder. This makes it easy to identify job(s) specific to the repository of interest.

## Library Blocks

The library job examples in this repository provide the library blocks below.

### Plan

It is often desirable to see what changes would result in IaC artifacts if the entry gate for a particular environment was confirmed. The changes can then be reviewed before the gate is actually accepted. The changes include
modifications to templates, as well as whatever "exchange change" information the cloud provider generates. As an example, for AWS, plan generates change sets for each affected cloud formtion template.

The Plan block creates a temporary branch in the IaC repo and then applies the changes that would be applied if a gate were confirmed. It captures the results of this "whatif" processing in the temporary branch.

While it is possible to produce plans for every environment (as shown in the example streams), typically any errors associated with lower environments can be dealt with as part of the normal deployment activity. The typical use for plan is before approving the entry gate for production to assist in confirmation that nothing unexpected will occur when the entry gate is confirmed.

### Update

The Update block records details of the code builds provided to the stream in the IaC repo. The particular builds currently employed in an environment are thus considered part of the config, so that the environment can be recreated in full at any time.

### Deploy

The Deploy block is responsible for taking the current (to be) state of the IaC repos and reflecting this the actual (as is) state of the environments within the cloud provider.

### Transfer

It is common to separate production environments in their own cloud account/subscription. In order for the production environment to be able to operate standalone, it is common to use a separate artifact registry for production. The Transfer block permits the transfer of build artifacts provided to a stream to be transferred between registries. It is only needed where stages in a stream transition between environments that are not co-located in the cloud provider e.g. the `release` stream in the overview diagram.

### Accept

The confirmation of the exit gate for an environment often needs to be recorded in change management systems for audit purposes. The Accept block thus permits details of who confirmed the exit gate, along with any test artifacts, to be forwarded to change management systems of record.

### Manage

The Manage block co-ordinates the creation of any infrastructure that may be required to be in place before application related changes can be deployed via stream jobs. While there is nothing in the design of the streams convention that says infrastructure can't be redeployed as part of a stream (via the Deploy block for instance), in general this is avoided as it increases the execution time of stream for no return.

## Jenkins pipeline steps

This repository is designed to be used as a Jenkins shared library to provide directly invocable step functions in pipelines. Each step is thus defined in its own file. Help for each step is provided in the corresponding `.txt` file, which also makes the help available under the "Pipeline Syntax -> Global Variable Reference" option of the menu shown when reviewing the pipeline job within the Jenkins server. The help files are formatted as html fragments.

### General logging

The following steps provide a general mechanism to log the occurrence of various events within pipelines to one or more collaboration channels. The selection
of the channels is left as a task for the calling pipeline. In the examples, variables for the channels are defined in a properties file in the product CMDB named using the lowercase equivalent of the partition. This is referred to via a `BASE_PROPERTIES` environment variable.

Also of note in the examples is that notifications for builds have their own notification channels (reflecting the separation between build and stream jobs), and stream jobs vary the channel details based on the environment stage currently being executed. An equally valid strategy would be to use channels per stream.

- notifyChannels
- notifyFailure
- notifySuccess

### Build Jobs

The following steps are provided to streamline the development of build pipelines.

- buildOpenapi
- installMaven
- installNode
- loadCMDB
- loadProperties
- notifyBuildFailure
- notifyBuildSuccess
- notifyQAFailure
- notifySetupFailure
- notifyTriggerStreamFailure
- runMavenTargets
- runNPMTargets
- runNPMTargetWithOptions
- setCloudProviderCredentials
- triggerStream
- uploadArtifactsToRegistry

### Stream Jobs

The following steps are provided to streamline the development of stream pipelines.

- notifyEntryConfirmation
- notifyExitConfirmation
- triggerAccept
- triggerDeploy
- triggerManage
- triggerPlan
- triggerTransfer
- triggerUpdate

### Library Jobs

The following steps are provided to streamline the development of library pipelines.

- notifyAcceptFailure
- notifyAcceptSuccess
- notifyDeployFailure
- notifyDeploySuccess
- notifyManageFailure
- notifyManageSuccess
- notifyPlanDeployFailure
- notifyPlanUpdateFailure
- notifyTransferFailure
- notifyTransferSuccess
- notifyUpdateFailure
- notifyUpdateSuccess
- setCloudProviderCredentials
