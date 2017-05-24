package guru.stefma.artifactorypublish

import guru.stefma.androidartifacts.ArtifactsExtension

class ArtifactoryPublishExtension extends ArtifactsExtension {

    String artifactoryUrl = ""

    String artifactoryRepo = ""

    String artifactoryUser

    String artifactoryKey

    String[] publications

}
