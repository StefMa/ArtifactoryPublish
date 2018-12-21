pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "guru.stefma.bintrayrelease") {
                useModule("guru.stefma.bintrayrelease:bintrayrelease:${requested.version}")
            }
            if (requested.id.id == "org.junit.platform.gradle.plugin") {
                useModule("org.junit.platform:junit-platform-gradle-plugin:$requested.version")
            }
        }
    }
}

rootProject.name = "artifactorypublish"