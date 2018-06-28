package guru.stefma.artifactorypublish

import org.gradle.api.Project

class ArtifactoryPublishConfiguration {

    ArtifactoryPublishExtension mExtension

    ArtifactoryPublishConfiguration(ArtifactoryPublishExtension extension) {
        mExtension = extension
    }

    void configure(Project project) {
        ArtifactoryPublishPropertyFinder propertyFinder = new ArtifactoryPublishPropertyFinder(project, mExtension)

        project.artifactory {
            contextUrl = mExtension.artifactoryUrl
            publish {
                repository {
                    repoKey = mExtension.artifactoryRepo
                    username = propertyFinder.artifactoryUser
                    password = propertyFinder.artifactoryKey
                }
                defaults {
                    // Currently we only support the `release` publication
                    publications ('releaseAar')
                    publishArtifacts = true
                    publishPom = true
                }
            }
        }
    }

}
