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
        classpath 'guru.stefma.artifactorypublish:artifactorypublish:0.0.1'
    }
}
```

Then put these into your **module** `build.gradle`:
```groovy
apply plugin: 'guru.stefma.artifactorypublish' // Add this after your android or java plugin!

artifactoryPublish {
    groupId = 'net.example'
    artifactId = 'artifactorypublish'
    publishVersion = "0.1"
    artifactoryUrl = "https://artifactory.example.com/"
    artifactoryRepo = "maven-repo"
}
```
