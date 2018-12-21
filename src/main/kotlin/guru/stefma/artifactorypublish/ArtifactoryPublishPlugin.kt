package guru.stefma.artifactorypublish

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A plugin which will automatically setup the artifactory closure
 * (from the [original jfrog artifactory plugin](https://github.com/jfrog/build-info))
 * to make it easily to upload to a artifactory.
 *
 * This will only setup everything correctly when either the **guru.stefma.androidartifacts**
 * or the **guru.stefma.javaartifacts** plugin is applied.
 * Otherwise it does nothing.
 */
class ArtifactoryPublishPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project.pluginManager) {
        withPlugin("guru.stefma.androidartifacts") {
            configurePlugin(project)
        }
        withPlugin("guru.stefma.javaartifacts") {
            configurePlugin(project)
        }
    }

    private fun configurePlugin(project: Project) = with(project) {
        pluginManager.apply("com.jfrog.artifactory")

        // Create our own extension which can be setup
        val extension = extensions.create("artifactoryPublish", ArtifactoryPublishExtension::class.java)

        afterEvaluate {
            // Configure the artifactory closure with our extension
            ArtifactoryPublishConfiguration(project, extension)
                    .configure()
        }
    }

}