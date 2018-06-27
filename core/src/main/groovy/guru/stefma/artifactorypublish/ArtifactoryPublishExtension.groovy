package guru.stefma.artifactorypublish

import guru.stefma.androidartifacts.AndroidArtifactsExtension

/**
 * A ArtifactoryExtension which takes the AndroidArtifactsExtension as argument to set the default properties for
 * this class.
 */
class ArtifactoryPublishExtension {

    private final AndroidArtifactsExtension mArtifactsExtension

    ArtifactoryPublishExtension(AndroidArtifactsExtension extension) {
        mArtifactsExtension = extension
    }

    String artifactoryUrl = ""

    String artifactoryRepo = ""

    String artifactoryUser

    String artifactoryKey

    void setArtifactId(final String artifactId) {
        mArtifactsExtension.artifactId = artifactId
    }

    void setJavadoc(final boolean javadoc) {
        mArtifactsExtension.javadoc = javadoc
    }

    void setSources(final boolean sources) {
        mArtifactsExtension.sources = sources
    }

    String getArtifactId(final String artifactId) {
        return mArtifactsExtension.artifactId
    }

    boolean getJavadoc(final boolean javadoc) {
        return mArtifactsExtension.javadoc
    }

    boolean getSources(final boolean sources) {
        return mArtifactsExtension.sources
    }

}
