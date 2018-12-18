package guru.stefma.artifactorypublish

import guru.stefma.androidartifacts.ArtifactsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jfrog.gradle.plugin.artifactory.ArtifactoryPlugin

/**
 * A plugin which will create artifacts for your android artifacts and automatically setup the artifactory
 * closure (from the "original" jfrog artifactory plugin) to make it easily to upload to a artifactory.
 */
class ArtifactoryPublishPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        // Apply the artifactory plugin
        project.pluginManager.apply(ArtifactoryPlugin::class.java)

        // Apply the artifacts plugin
        project.pluginManager.apply("guru.stefma.artifacts")

        project.pluginManager.withPlugin("guru.stefma.androidartifacts") {
            configurePlugin(project)
        }
        project.pluginManager.withPlugin("guru.stefma.javaartifacts") {
            configurePlugin(project)
        }
    }

    private fun configurePlugin(project: Project) {
        val artifactsExtension = project.extensions.getByType(ArtifactsExtension::class.java)

        // Create our own extension which can be setup
        val extension =
                project.extensions.create("artifactoryPublish", ArtifactoryPublishExtension::class.java, artifactsExtension)

        project.afterEvaluate {
            // Configure the artifactory closure with our extension
            ArtifactoryPublishConfiguration(project, extension)
                    .configure()
        }
    }

}