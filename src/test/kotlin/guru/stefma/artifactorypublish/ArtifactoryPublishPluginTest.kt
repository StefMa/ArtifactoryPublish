package guru.stefma.artifactorypublish

import guru.stefma.artifactorypublish.rule.ProjectSetupExtension
import org.assertj.core.api.Assertions.assertThat
import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.io.File

@ExtendWith(ProjectSetupExtension::class)
class ArtifactoryPublishPluginTest(
        private val javaProjectDir: File,
        private val androidProjectDir: File
) {

    @Test
    fun `apply plugin without android-library or java-library should log and do nothing`() {
        // We override the build script here and just apply the plugin
        val buildScript = File(javaProjectDir, "build.gradle")
        buildScript.writeText(
                """
                            plugins {
                                id 'guru.stefma.artifactorypublish'
                            }
                    """)

        val build = GradleRunner.create()
                .withProjectDir(javaProjectDir)
                .withPluginClasspath()
                .withArguments("help", "--info")
                .build()

        assertThat(build.output).contains(
                "You have to apply either the `com.android.library` plugin or the `java-library` plugin"
        )
    }

    @ParameterizedTest(name = "GradleVersion {0} should be build/fail (1 = true; 0 = false): {1}")
    @CsvSource(
            value = [
                "4.5, 1",
                "4.5.1, 1",
                "4.6, 1",
                "4.7, 1",
                "4.8, 1",
                "4.8.1, 1",
                "4.9, 1"
            ]
    )
    fun `test publishArtifactory task in an java project with different gradle versions`(
            gradleVersion: String,
            shouldPass: String
    ) {
        GradleRunner.create()
                .withProjectDir(javaProjectDir)
                .withPluginClasspath()
                .withArguments("artifactoryPublish", "-PartifactoryUser=admin", "-PartifactoryKey=password", "--stacktrace")
                .withGradleVersion(gradleVersion)
                .apply { if (shouldPass == "1") build() else buildAndFail() }
    }

    @ParameterizedTest(name = "GradleVersion {0} should be build/fail (1 = true; 0 = false): {1}")
    @CsvSource(
            value = [
                "4.5, 1",
                "4.5.1, 1",
                "4.6, 1",
                "4.7, 1",
                "4.8, 1",
                "4.8.1, 1",
                "4.9, 1"
            ]
    )
    fun `test publishArtifactory task in an android project with different gradle versions`(
            gradleVersion: String,
            shouldPass: String
    ) {
        GradleRunner.create()
                .withProjectDir(androidProjectDir)
                .withPluginClasspath()
                .withGradleVersion(gradleVersion)
                .withArguments("artifactoryPublish", "-PartifactoryUser=admin", "-PartifactoryKey=password", "--stacktrace")
                .apply { if (shouldPass == "1") build() else buildAndFail() }
    }

}