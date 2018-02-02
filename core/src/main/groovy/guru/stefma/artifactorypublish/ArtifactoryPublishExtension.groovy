package guru.stefma.artifactorypublish

import com.novoda.gradle.release.PublishExtension
import org.codehaus.groovy.runtime.InvokerHelper

/**
 * A ArtifactoryExtension which extends from PublishExtension to make sure that we can use one closure (our own)
 * to use it for a bintray & a artifactory upload!
 *
 * You should use the `copyPropertiesTo` method to keep the PublishExtension from the BintrayRelease plugin in sync.
 */
class ArtifactoryPublishExtension extends PublishExtension {

    String artifactoryUrl = ""

    String artifactoryRepo = ""

    String artifactoryUser

    String artifactoryKey

    /**
     * Copy properties from *this* to the given PublishExtension.
     *
     * So we can make sure that the `artifactoryPublish` extension can be used for the `bintrayUpload` as well...
     *
     * @param publishExtension the extension to setup. Should come from the com.novoda.binray-release plugin.
     */
    void copyPropertiesTo(PublishExtension publishExtension) {
        def properties = this.properties
        properties.remove("metaClass")
        properties.remove("class")
        InvokerHelper.setProperties(publishExtension, properties)
    }

}
