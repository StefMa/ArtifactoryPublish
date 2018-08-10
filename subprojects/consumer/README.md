# A test project for ArtifactoryPublish
The [`android`](android/) and the [`java`](java/) sample contains a simple class
with a static `helloWorld()` method.

The containing build scripts ([android](android/build.gradle) & [java](java/build.gradle)) 
are fully setup to publish the libraries to an Artifactory.

## Testing
### Install Artifactory
To test this project you need a installed Artifactory instance.
If you already have a installed instance somewhere you are ready to go. 
Just jump over this section.

if not you can simply install a Artifactory instance via Docker:
```
docker run --name artifactory -d -p 8081:8081 docker.bintray.io/jfrog/artifactory-oss:latest
```
The instance will be then available at [http://localhost:8081/artifactory/](http://localhost:8081/artifactory/).

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
Now you should be able to consum the published libraries by create a new project with the following dependencies:
```groovy
repositories {
    maven { url "http://localhost:8081/artifactory/example-repo-local" }
}

dependencies {
    implementation "guru.stefma.sample:android:0.0.1"
    implementation "guru.stefma.sample:java:0.0.1"
}
```