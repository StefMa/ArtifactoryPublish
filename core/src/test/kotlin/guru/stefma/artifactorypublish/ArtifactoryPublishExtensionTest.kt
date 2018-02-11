package guru.stefma.artifactorypublish

import com.novoda.gradle.release.PublishExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ArtifactoryPublishExtensionTest {

    @Test
    fun `copyPropertiesTo should copy properties`() {
        val publishExtension = ArtifactoryPublishExtension()
        publishExtension.bintrayUser = "User"
        publishExtension.bintrayKey = "Key"
        val propertiesCopiedTo = PublishExtension()

        publishExtension.copyPropertiesTo(propertiesCopiedTo)

        assertThat(propertiesCopiedTo.bintrayUser).isEqualTo("User")
        assertThat(propertiesCopiedTo.bintrayKey).isEqualTo("Key")
    }
}