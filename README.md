[![CircleCI](https://img.shields.io/circleci/project/github/StefMa/ArtifactoryPublish.svg)](https://circleci.com/gh/StefMa/ArtifactoryPublish)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[ ![Download](https://api.bintray.com/packages/stefma/maven/ArtifactoryPublish/images/download.svg) ](https://bintray.com/stefma/maven/ArtifactoryPublish/_latestVersion)

# ArtifactoryPublish

A super easy way to release Android and Java artifacts to artifactory.

## Description
This is a simple helper to simplify the configuration of the artifactory plugin.

## How to use it
### Apply the plugin
Since the plugin is **not** available via the [Gradle Plugin Portal](https://plugins.gradle.org) you have to put these 
to the **top** of your `settings.gradle`:
```groovy
pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "guru.stefma.artifactorypublish") {
                useModule("guru.stefma.artifactorypublish:artifactorypublish:${requested.version}")
            }
        }
    }
}
``` 

Now you are able to apply the plugin in the [`plugins` block](https://docs.gradle.org/4.5/userguide/plugins.html#sec:plugins_block):
```groovy
plugins {
    // The current version can be found here https://git.io/vdsOs
    id "guru.stefma.artifactorypublish" version "$artifactoryPublishVersion" 
}
```

<details>
<summary>Use the old way</summary>

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

apply plugin: "guru.stefma.artifactorypublish"
```

</details>

> **Note:** The plugin should be applied **after** the java-library or com.android.library plugin!

### Configuration
To configure the plugin:

```groovy
artifactoryPublish {
    groupId = "net.example"
    artifactId = "artifactorypublish"
    publishVersion = "0.1"
    artifactoryUrl = "https://artifactory.example.com/"
    artifactoryRepo = "maven-repo"
}
```

All configuration options are:

| Property | Description | Mandatory | Property finder* |
|-|-|-|-|
| artifactoryUrl | Url to your artifactory | ✅ | ❌ |
| artifactoryRepo | Repository name | ✅ | ❌ |
| artifactoryUser | Artifactory username | ✅ | ✅ |
| artifactoryKey | API Key or Password | ✅ | ✅ |

> *could be added via closure extension, terminal or property file. 

### Publish
To publish your library to artifactory simply run
```
./gradlew clean build artifactoryPublish -PartifactoryUser=ARTIFACTORY_USERNAME -PartifactoryKey=ARTIFACTORY_KEY
```
