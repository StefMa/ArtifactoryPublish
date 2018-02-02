package guru.stefma.artifactorypublish

import com.novoda.gradle.release.PublishExtension

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
     * Should be always in sync with updates. So we can make sure that the  `artifactoryPublish` extension can
     * be used for the `bintrayUpload` as well...
     *
     * @param publishExtension the extension to setup. Should come from the com.novoda.binray-release plugin.
     */
    void copyPropertiesTo(PublishExtension publishExtension) {
        // TODO: Split into required properties and optional one
        publishExtension.with {
            publishVersion = this.publishVersion
            dryRun = this.dryRun
            bintrayKey = this.bintrayKey
            bintrayUser = this.bintrayUser
            artifactId = this.artifactId
            autoPublish = this.autoPublish
            desc = this.desc
            description = this.description
            groupId = this.groupId
            issueTracker = this.issueTracker
            licences = this.licences
            publications = this.publications
            repoName = this.repoName
            version = this.version
            repository = this.repository
            uploadName = this.uploadName
            userOrg = this.userOrg
            versionAttributes = this.versionAttributes
            website = this.website
        }
    }

}
