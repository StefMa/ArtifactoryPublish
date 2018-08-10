[![CircleCI](https://circleci.com/gh/StefMa/ArtifactoryPublish.svg?style=svg)](https://circleci.com/gh/StefMa/ArtifactoryPublish)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[ ![Download](https://api.bintray.com/packages/stefma/maven/ArtifactoryPublish/images/download.svg) ](https://bintray.com/stefma/maven/ArtifactoryPublish/_latestVersion)

# ArtifactoryPublish

A super easy way to release Android and Java artifacts to Artifactory.

## Description
This is a simple helper to simplify the configuration of the [Artifactory plugin](https://www.jfrog.com/confluence/display/RTF/Gradle+Artifactory+Plugin).

## How to use it
### Apply the plugin
Put this into your **project** `build.gradle`:
```groovy
buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        // The current version can be found here https://git.io/vdsOs
        classpath "guru.stefma.artifactorypublish:artifactorypublish:$artifactoryPublishVersion"
    }
}
```

Then you are able to apply the `guru.stefma.artifactorypublish` plugin:
```groovy
apply plugin: "com.android.library"
apply plugin: "org.jetbrains.kotlin-android" // <1>
apply plugin: "guru.stefma.artifactorypublish" // <2>

version = "1.0.0"
group = "guru.stefma.artifactorypublish"
artifactoryPublish {
    artifactId = 'artifactorypublish'
    artifactoryRepo = "example-repo-local"
    artifactoryUrl = "http://localhost:8081/artifactory"
    publications = ["releaseAar"] // <3>
}
```
* **//1:** The Kotlin plugin is optional of course. But if you add it, it will generate a KDoc together with a javadoc.
* **//2:** The **ArtifactoryPublish** plugin should always be added **after** the android library and kotlin-android plugin.
* **//3:** To see a list of **all available publications** just run the `androidArtifactGeneratedPublications` task.

### Configuration
There are several configuration options. The following table give you a overview about all of them:

| Property | Description | Mandatory | Property finder* |
|-|-|-|-|
| artifactId | The artifact id | ✅ | ❌ |
| artifactoryUrl | Url to your artifactory | ✅ | ❌ |
| artifactoryRepo | The Repository name | ✅ | ❌ |
| artifactoryUser | Artifactory username | ✅ | ✅ |
| artifactoryKey | API Key or Password | ✅ | ✅ |
| publications | The publications you want to publish | ✅ | ❌ |

> *can be added via [Gradle properties](https://docs.gradle.org/current/userguide/build_environment.html)

### Publish
To publish your library to artifactory simply run
```
./gradlew clean build artifactoryPublish -PartifactoryUser=ARTIFACTORY_USERNAME -PartifactoryKey=ARTIFACTORY_KEY
```
