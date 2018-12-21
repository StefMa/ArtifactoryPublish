package guru.stefma.artifactorypublish

/**
 * The extension for this plugin.
 */
open class ArtifactoryPublishExtension {

    var artifactoryUrl = ""

    var artifactoryRepo = ""

    var artifactoryUser: String? = null

    var artifactoryKey: String? = null

    var publications = emptyArray<String>()

}

