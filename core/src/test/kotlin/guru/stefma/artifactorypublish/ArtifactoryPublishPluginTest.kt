package guru.stefma.artifactorypublish

import guru.stefma.artifactorypublish.rule.ProjectSetupExtension
import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.Disabled
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
                "4.8.1, 1"
            ]
    )
    @Disabled(value = "Java projects aren't supported")
    fun `testJavaProjects`(gradleVersion: String, shouldPass: String) {
        GradleRunner.create()
                .withProjectDir(javaProjectDir)
                .withPluginClasspath()
                .withArguments("build")
                .withGradleVersion(gradleVersion)
                .apply { if (shouldPass == "1") build() else buildAndFail() }
    }

    @ParameterizedTest(name = "GradleVersion {0} should be build/fail (1 = true; 0 = false): {1}")
    @CsvSource(
            value = [
                "4.8.1, 1"
            ]
    )
    fun `testAndroidProjects`(gradleVersion: String, shouldPass: String) {
        GradleRunner.create()
                .withProjectDir(androidProjectDir)
                .withPluginClasspath()
                .withGradleVersion(gradleVersion)
                .withArguments("build")
                .apply { if (shouldPass == "1") build() else buildAndFail() }
    }

}