# A test project for ArtifactoryPublish
The [`android`](android/) sample contains a simple class called `AndroidSample` with a 
static `helloWorld()` method.

The containing [build.gradle](android/build.gradle) is fully setup to publish 
this library to a Artifactory.

## Testing
### Artifactory
To test this project you need a installed Artifactory instance.
If you already have a installed instance somewhere you are ready to go. 
Just jump over this section.

#### Artifactory
You can simply install a Artifactory instance via Docker:
```
docker run --name artifactory -d -p 8081:8081 docker.bintray.io/jfrog/artifactory-oss:latest
```
[(See here for more)](https://www.jfrog.com/confluence/display/RTF/Installing+with+Docker)

### Publish
After you have successfully setup the Artifactory you can finally publish
the library project by running:
```
./gradlew build artifactoryPublish -PartifactoryUser=admin -PartifactoryKey=password
```

> **Note:** If you have already are installed Artifactory instance you have to use your user and your API-Key instead 
of the [Default Admin User](https://www.jfrog.com/confluence/display/RTF/Installing+Artifactory#InstallingArtifactory-DefaultAdminUser) credentials.

### Consume the lib
Now you should be able to consumer the already published library by create a new project with the following setup:
```groovy
plugins {
    id "java-library"
}

repositories {
    maven { url "http://localhost:8081/artifactory/example-repo-local" }
}

dependencies {
    implementation "guru.stefma.sample:android:0.0.1"
}
```