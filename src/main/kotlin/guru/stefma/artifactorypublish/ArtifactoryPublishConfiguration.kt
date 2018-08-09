package guru.stefma.artifactorypublish

import guru.stefma.artifactorypublish.closure.closureOf
import org.gradle.api.Project
import org.jfrog.gradle.plugin.artifactory.dsl.ArtifactoryPluginConvention
import org.jfrog.gradle.plugin.artifactory.dsl.PublisherConfig
import org.jfrog.gradle.plugin.artifactory.task.ArtifactoryTask

class ArtifactoryPublishConfiguration(
        private val project: Project,
        private val extension: ArtifactoryPublishExtension
) {

    private val propertyFinder = ArtifactoryPublishPropertyFinder(project, extension)

    fun configure() {
        project.extensions.getByType(ArtifactoryPluginConvention::class.java).apply {
            setContextUrl(extension.artifactoryUrl)
            publish(closureOf<PublisherConfig> {
                repository(closureOf<PublisherConfig.Repository> {
                    setRepoKey(extension.artifactoryRepo)
                    setUsername(propertyFinder.artifactoryUser)
                    setPassword(propertyFinder.artifactoryKey)
                })
            })
        }
        (project.tasks.getByName("artifactoryPublish") as ArtifactoryTask).apply {
            // Currently we only support the `release` publication
            publications("releaseAar")
            setPublishArtifacts(true)
            setPublishPom(true)
        }
    }

}