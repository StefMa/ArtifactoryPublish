package guru.stefma.artifactorypublish

import org.gradle.api.Project

class ArtifactoryPublishConfiguration {

    ArtifactoryPublishExtension mExtension

    ArtifactoryPublishConfiguration(ArtifactoryPublishExtension extension) {
        mExtension = extension
    }

    void configure(Project project) {
        ArtifactoryPublishPropertyFinder propertyFinder = new ArtifactoryPublishPropertyFinder(project, mExtension)

        def publication = mExtension.publications ?: project.plugins.hasPlugin('com.android.library') ? ['release'].toArray() : ['maven'].toArray()
        project.logger.debug("Set publication to : " + publication)

        project.artifactory {
            contextUrl = mExtension.artifactoryUrl
            publish {
                repository {
                    repoKey = mExtension.artifactoryRepo
                    username = propertyFinder.artifactoryUser
                    password = propertyFinder.artifactoryKey
                }
                defaults {
                    publications publication
                    publishArtifacts = true
                    publishPom = true
                }
            }
        }
    }

}
