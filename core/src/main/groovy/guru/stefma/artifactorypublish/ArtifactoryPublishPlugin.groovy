package guru.stefma.artifactorypublish

import guru.stefma.androidartifacts.AndroidArtifactsPlugin
import guru.stefma.androidartifacts.ArtifactsExtension
import org.gradle.api.Project
import org.jfrog.gradle.plugin.artifactory.ArtifactoryPlugin

class ArtifactoryPublishPlugin extends AndroidArtifactsPlugin {

    ArtifactoryPublishExtension mPublishExtension

    @Override
    void apply(Project project) {
        super.apply(project)

        new ArtifactoryPlugin().apply(project)
        project.afterEvaluate {
            project.apply([plugin: 'com.jfrog.artifactory'])
            new ArtifactoryPublishConfiguration(mPublishExtension).configure(project)
        }
    }

    @Override
    ArtifactsExtension getArtifactsExtensions(Project project) {
        mPublishExtension = project.extensions.create('artifactoryPublish', ArtifactoryPublishExtension)
        return mPublishExtension
    }
}
