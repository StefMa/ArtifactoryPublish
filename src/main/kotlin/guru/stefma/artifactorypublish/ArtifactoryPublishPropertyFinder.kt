package guru.stefma.artifactorypublish

import org.gradle.api.Project

class ArtifactoryPublishPropertyFinder(
        private val project: Project,
        private val extension: ArtifactoryPublishExtension
) {

    val artifactoryUser get() = getString(project, "artifactoryUser", extension.artifactoryUser)

    val artifactoryKey get() = getString(project, "artifactoryKey", extension.artifactoryKey)

    private fun getString(project: Project, propertyName: String, defaultValue: String?) =
            project.properties[propertyName] as? String ?: defaultValue

}