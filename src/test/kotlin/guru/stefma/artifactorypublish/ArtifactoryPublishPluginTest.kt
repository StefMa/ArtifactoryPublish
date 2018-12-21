package guru.stefma.artifactorypublish

import appendAndroidExtension
import guru.stefma.artifactorypublish.rule.ProjectSetupExtension
import org.assertj.core.api.Assertions.assertThat
import org.gradle.api.file.Directory
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

    @ParameterizedTest(name = "GradleVersion {0} should be build/fail (1 = true; 0 = false): {1}")
    @CsvSource(
            value = [
                "4.5, 1",
                "4.5.1, 1",
                "4.6, 1",
                "4.7, 1",
                "4.8, 1",
                "4.8.1, 1",
                "4.9, 1",
                "4.10.3, 1"
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
                "4.9, 1",
                "4.10.3, 1"
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

    @Test
    fun `test apply plugin only does nothing`() {
        File(javaProjectDir, "build.gradle").apply {
            writeText("""
                plugins {
                    id 'guru.stefma.artifactorypublish'
                }
            """.trimIndent())
        }
        val result = GradleRunner.create()
                .withProjectDir(javaProjectDir)
                .withPluginClasspath()
                .withArguments("tasks")
                .build()

        assertThat(result.output).doesNotContain("artifactoryPublish")
    }

    @Test
    fun `test apply plugin with artifacts plugin without java-library does nothing`() {
        File(javaProjectDir, "build.gradle").apply {
            writeText("""
                plugins {
                    id 'guru.stefma.artifactorypublish'
                    id 'guru.stefma.artifacts'
                }
            """.trimIndent())
        }
        val result = GradleRunner.create()
                .withProjectDir(javaProjectDir)
                .withPluginClasspath()
                .withArguments("tasks")
                .build()

        assertThat(result.output).doesNotContain("artifactoryPublish")
        assertThat(result.output).doesNotContain("androidArtifactJava")
    }

    @Test
    fun `test apply plugin with artifacts plugin and java-library contains tasks on java`() {
        File(javaProjectDir, "build.gradle").apply {
            writeText("""
                plugins {
                    id 'guru.stefma.artifactorypublish'
                    id 'guru.stefma.artifacts'
                    id 'java-library'
                }
            """.trimIndent())
        }
        val result = GradleRunner.create()
                .withProjectDir(javaProjectDir)
                .withPluginClasspath()
                .withArguments("tasks")
                .build()

        assertThat(result.output).contains("artifactoryPublish")
        assertThat(result.output).contains("androidArtifactJava")
    }

    @Test
    fun `test apply plugin with artifacts plugin and java-library contains tasks on android`() {
        File(androidProjectDir, "build.gradle").apply {
            writeText("""
                plugins {
                    id 'guru.stefma.artifactorypublish'
                    id 'guru.stefma.artifacts'
                    id 'com.android.library'
                }

                ${appendAndroidExtension()}
            """.trimIndent())
        }
        val result = GradleRunner.create()
                .withProjectDir(androidProjectDir)
                .withPluginClasspath()
                .withArguments("tasks")
                .build()

        assertThat(result.output).contains("artifactoryPublish")
        assertThat(result.output).contains("androidArtifactRelease")
    }
}