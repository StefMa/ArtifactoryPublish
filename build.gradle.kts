import guru.stefma.bintrayrelease.PublishExtension

plugins {
    `java-gradle-plugin`
    kotlin("jvm") version "1.3.11"
    id("java-library")
    id("guru.stefma.bintrayrelease") version "1.0.0" apply false
}
apply(plugin = "guru.stefma.bintrayrelease")

gradlePlugin {
    plugins {
        create("artifactorypublish") {
            id = "guru.stefma.artifactorypublish"
            implementationClass = "guru.stefma.artifactorypublish.ArtifactoryPublishPlugin"
        }
    }
}

tasks.withType(Test::class.java) {
    useJUnitPlatform()
}

repositories {
    google()
    jcenter()
}

val optionalPlugins by configurations.creating

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jfrog.buildinfo:build-info-extractor-gradle:4.8.1")
    implementation("guru.stefma.androidartifacts:androidartifacts:1.4.0")

    optionalPlugins("com.android.tools.build:gradle:3.1.4")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.3.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.3.2")
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0")
}

// This will add the android tools into the "test classpath"
tasks.withType<PluginUnderTestMetadata> {
    pluginClasspath.from(optionalPlugins)

    // We have to remove guava-18 here because
    // jfrog.buildinfo brings it as transitive dependecy
    // the AGP uses guava-22.
    // Unfourtaly Gradle picks 18 at test time
    // which leads to a crash
    // See also https://discuss.gradle.org/t/manage-transitive-dependencies-with-testkit/29949
    val classpathWithoutGuava18 = pluginClasspath.files.filter { !it.path.contains("guava-18.0") }
    pluginClasspath.setFrom(classpathWithoutGuava18)
}

group = "guru.stefma.artifactorypublish"
version = "1.1.0"
configure<PublishExtension> {
    userOrg = "stefma"
    artifactId = "artifactorypublish"
    uploadName = "ArtifactoryPublish"
    desc = "Super duper easy way to release your Android and other artifacts to artifactory"
    website = "https://github.com/StefMa/ArtifactoryPublish"
    setLicences("MIT")
}
