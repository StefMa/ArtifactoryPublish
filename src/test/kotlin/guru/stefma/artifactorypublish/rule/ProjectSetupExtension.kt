package guru.stefma.artifactorypublish.rule

import org.junit.jupiter.api.extension.*
import java.io.File

/**
 * A extension which will creates temporary folders for you and put valid gradle scripts in it.
 *
 * If you use these extension you have to provide a constructor with two [File] params.
 * The first one will be the project to a valid **java buildScript**.
 * The second one will be a projectDir to a valid **android buildScript**.
 *
 * Must be registered at class level.
 */
class ProjectSetupExtension : BeforeAllCallback, AfterAllCallback, ParameterResolver {

    private var javaProjectDir: File = createTempDir(suffix = "", prefix = "java")

    private var androidProjectDir: File = createTempDir(suffix = "", prefix = "android")

    /**
     * Create the buildScripts
     */
    override fun beforeAll(context: ExtensionContext?) {
        createJavaBuildScript()
        createAndroidBuildScript()
    }

    private fun createJavaBuildScript() {
        val buildScript = File(javaProjectDir, "build.gradle")
        buildScript.writeText(
                """
                            plugins {
                                id 'java-library'
                                id 'guru.stefma.artifactorypublish' apply false
                            }
                            apply plugin: "guru.stefma.artifactorypublish"

                            repositories {
                                jcenter()
                                google()
                            }

                            dependencies {
                                implementation "junit:junit:4.12"
                            }

                            version = "0.1"
                            group = "net.example.java"
                            artifactoryPublish {
                                artifactId = 'artifactorypublish'

                                artifactoryRepo = "example-repo-local"
                                artifactoryUrl = "http://localhost:8081/artifactory"
                                publications = ["maven"]
                            }
                        """
        )
    }

    private fun createAndroidBuildScript() {
        val buildScript = File(androidProjectDir, "build.gradle")
        buildScript.writeText(
                """
                            buildscript {
                                repositories {
                                    jcenter()
                                    google()
                                }
                                dependencies {
                                    classpath 'com.android.tools.build:gradle:3.0.1'
                                }
                            }

                            plugins {
                                id 'guru.stefma.artifactorypublish' apply false
                            }

                            apply plugin: "com.android.library"
                            apply plugin: "guru.stefma.artifactorypublish"

                            android {
                                compileSdkVersion 26
                                buildToolsVersion "26.0.2"
                                defaultConfig {
                                    minSdkVersion 16
                                    versionCode 1
                                    versionName "0.0.1"
                                }
                            }

                            // Disable lint. No need to run it...
                            tasks.lint.enabled = false

                            repositories {
                                jcenter()
                                google()
                            }

                            dependencies {
                                implementation "junit:junit:4.12"
                            }

                            version = "0.1"
                            group = "net.example.android"
                            artifactoryPublish {
                                artifactId = 'artifactorypublish'

                                artifactoryRepo = "example-repo-local"
                                artifactoryUrl = "http://localhost:8081/artifactory"
                                publications = ["releaseAar"]
                            }
                        """
        )

        File(androidProjectDir, "/src/main/AndroidManifest.xml").apply {
            parentFile.mkdirs()
            writeText("<manifest package=\"guru.stefma.artifactorypublish.test\"/>")
        }
    }

    /**
     * Clean up. Delete the tempDirs ([javaProjectDir] & [androidProjectDir])
     */
    override fun afterAll(context: ExtensionContext?) {
        javaProjectDir.deleteRecursively()
        androidProjectDir.deleteRecursively()
    }

    /**
     * If parameter type type [File] and at index 0 or 1 then....
     */
    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        return (parameterContext.parameter.type == File::class.java) && (parameterContext.index == 0 || parameterContext.index == 1)
    }

    /**
     * ...return at index 0 the [javaProjectDir] and at index 1 the [androidProjectDir]
     */
    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        return when (parameterContext.index) {
            0 -> javaProjectDir
            1 -> androidProjectDir
            else -> throw IllegalArgumentException()
        }
    }

}