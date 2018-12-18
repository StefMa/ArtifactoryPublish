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

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jfrog.buildinfo:build-info-extractor-gradle:4.7.5")
    implementation("guru.stefma.androidartifacts:androidartifacts:1.1.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.2.0")
    testImplementation("org.assertj:assertj-core:3.10.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0-RC1")
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
