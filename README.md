[![CircleCI](https://circleci.com/gh/StefMa/ArtifactoryPublish.svg?style=svg)](https://circleci.com/gh/StefMa/ArtifactoryPublish)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Download](https://api.bintray.com/packages/stefma/maven/ArtifactoryPublish/images/download.svg) ](https://bintray.com/stefma/maven/ArtifactoryPublish/_latestVersion)

# ArtifactoryPublish
A super easy way to release Android and Java artifacts to Artifactory.

## Description
This is a helper to simplify the configuration of the [Artifactory Gradle plugin](https://www.jfrog.com/confluence/display/RTF/Gradle+Artifactory+Plugin).

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
        classpath "guru.stefma.androidartifacts:androidartifacts:$androidArtifactsVersion" // <1>
    }
}
```

Then you are able to apply the `guru.stefma.artifactorypublish` plugin:
```groovy
apply plugin: "com.android.library"
apply plugin: "guru.stefma.artifacts" // <1>
apply plugin: "guru.stefma.artifactorypublish" 

artifactoryPublish {
    artifactoryRepo = "example-repo-local"
    artifactoryUrl = "http://localhost:8081/artifactory"
    publications = ["releaseAar"] // <2>
}
```
* **//1:** This plugin requires the availability of the `guru.stefma.artifacts` plugin. 
See [here](https://github.com/StefMa/AndroidArtifacts) more information.
* **//2:** To see a list of **all available publications** just run the `androidArtifactGeneratedPublications` task.

### Configuration
There are several configuration options. The following table give you a overview about all of them:

| Property | Description | Mandatory | Property finder* |
|-|-|-|-|
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
