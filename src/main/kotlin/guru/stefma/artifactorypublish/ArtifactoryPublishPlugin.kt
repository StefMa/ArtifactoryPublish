package guru.stefma.artifactorypublish

import guru.stefma.androidartifacts.AndroidArtifactsPlugin
import guru.stefma.androidartifacts.ArtifactsExtension
import guru.stefma.androidartifacts.JavaArtifactsPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.LogLevel
import org.jfrog.gradle.plugin.artifactory.ArtifactoryPlugin

/**
 * A plugin which will create artifacts for your android artifacts and automatically setup the artifactory
 * closure (from the "original" jfrog artifactory plugin) to make it easily to upload to a artifactory.
 */
class ArtifactoryPublishPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        // Apply the artifactory plugin
        project.plugins.apply(ArtifactoryPlugin::class.java)

        when {
            project.plugins.hasPlugin("com.android.library") -> {
                // Apply the AndroidArtifactsPlugin on Android projects
                project.plugins.apply(AndroidArtifactsPlugin::class.java)
            }
            project.plugins.hasPlugin("java-library") -> {
                // ...and the JavaArtifactsPlugin on pure Java projects
                project.plugins.apply(JavaArtifactsPlugin::class.java)
            }
            else -> {
                project.logger.log(
                        LogLevel.INFO,
                        "You have to apply either the `com.android.library` plugin or the `java-library` plugin...")
                return
            }
        }
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