package guru.stefma.artifactorypublish

import guru.stefma.androidartifacts.ArtifactsExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ArtifactoryPublishExtensionTest {

    @Test
    fun `publishExtension should delegate correctly to artifactsExtension`() {
        val artifactsExtension = ArtifactsExtension()
        val publishExtension = ArtifactoryPublishExtension(artifactsExtension)
        publishExtension.javadoc = true
        publishExtension.artifactId = "id"
        publishExtension.sources = false

        assertThat(artifactsExtension.artifactId).isEqualTo("id")
        assertThat(artifactsExtension.javadoc).isTrue()
        assertThat(artifactsExtension.sources).isFalse()
    }
}