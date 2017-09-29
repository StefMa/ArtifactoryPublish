[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[ ![Download](https://api.bintray.com/packages/stefma/maven/ArtifactoryPublish/images/download.svg) ](https://bintray.com/stefma/maven/ArtifactoryPublish/_latestVersion)

# ArtifactoryPublish

A super easy way to release Android and Java artifacts to artifactory.

## Description
This is a simple helper to simplify the configuration of the artifactory plugin.

## How to use it
You can use it as a standalone plugin in the following way:

Put these lines into your **project** `build.gradle`
```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        // The current version can be found here https://git.io/vdsOs
        classpath "guru.stefma.artifactorypublish:artifactorypublish:$artifactoryPublishVersion"
    }
}
```

Then put these into your **module** `build.gradle`:
```groovy
apply plugin: 'guru.stefma.artifactorypublish' // Add this after your `com.android.library` or `java` plugin!

artifactoryPublish {
    groupId = 'net.example'
    artifactId = 'artifactorypublish'
    publishVersion = "0.1"
    artifactoryUrl = "https://artifactory.example.com/"
    artifactoryRepo = "maven-repo"
}
```

## Publish
To finally publish your lib to artifactory run
```
./gradlew clean build artifactoryPublish -PartifactoryUser=ARTIFACTORY_USERNAME -PartifactoryKey=ARTIFACTORY_KEY
```
