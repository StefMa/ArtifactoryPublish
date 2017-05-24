package guru.stefma.artifactorypublish

import org.gradle.api.Project

class ArtifactoryPublishPropertyFinder {

    private final Project mProject

    private final ArtifactoryPublishExtension mExtension

    ArtifactoryPublishPropertyFinder(Project project, ArtifactoryPublishExtension extension) {
        mProject = project
        mExtension = extension
    }

    def getArtifactoryUser() {
        getString(mProject, 'artifactoryUser', mExtension.artifactoryUser)
    }

    def getArtifactoryKey() {
        getString(mProject, 'artifactoryKey', mExtension.artifactoryKey)
    }

    private static String getString(Project project, String propertyName, String defaultValue) {
        project.hasProperty(propertyName) ? project.getProperty(propertyName) : defaultValue
    }

}
