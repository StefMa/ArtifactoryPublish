package guru.stefma.artifactorypublish

import guru.stefma.androidartifacts.AndroidArtifactsExtension
import guru.stefma.androidartifacts.AndroidArtifactsPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jfrog.gradle.plugin.artifactory.ArtifactoryPlugin

/**
 * A plugin which will create artifacts for your android artifacts and automatically setup the artifactory
 * closure (from the "original" jfrog artifactory plugin) to make it easily to upload to a artifactory.
 */
class ArtifactoryPublishPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        // Apply the artifactory plugin
        project.plugins.apply(ArtifactoryPlugin.class)
        // Apply the AndroidArtifacts plugin
        project.plugins.apply(AndroidArtifactsPlugin.class)
        AndroidArtifactsExtension artifactsExtension = project.extensions.getByType(AndroidArtifactsExtension.class)

        // Create our own extension which can be setup
        def extension =
                project.extensions.create('artifactoryPublish', ArtifactoryPublishExtension.class, artifactsExtension)

        project.afterEvaluate {
            // Configure the artifactory closure with our extension
            new ArtifactoryPublishConfiguration(extension).configure(project)
        }
    }

}
