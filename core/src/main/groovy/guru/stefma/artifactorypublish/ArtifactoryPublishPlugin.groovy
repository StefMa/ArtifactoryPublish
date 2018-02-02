package guru.stefma.artifactorypublish

import com.novoda.gradle.release.PublishExtension
import com.novoda.gradle.release.ReleasePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jfrog.gradle.plugin.artifactory.ArtifactoryPlugin

/**
 * A plugin which will create artifacts for your android and java artifacts and automatically setup the artifactory
 * closure (from the "original" jfrog artifactory plugin) to make it easily to upload to a artifactory.
 */
class ArtifactoryPublishPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        // Create our own extension which can be setup
        def extension = project.extensions.create('artifactoryPublish', ArtifactoryPublishExtension.class)

        // Apply the artifactory plugin
        project.plugins.apply(ArtifactoryPlugin.class)

        project.afterEvaluate {
            // Setup the PublishRelease extension based on the ArtifactoryPublish extension.
            def publishExtension = project.extensions.findByName("publish") as PublishExtension
            extension.copyPropertiesTo(publishExtension)

            // Configure the artifactory closure with our extension
            new ArtifactoryPublishConfiguration(extension).configure(project)
        }

        // Apply bintray-release (which will create the PublishRelease extension internal)
        // Have to applied *after* our evaluation listener otherwise its own evaluation listener
        // will run before ours which leads to crashes...
        project.plugins.apply(ReleasePlugin.class)
    }

}
