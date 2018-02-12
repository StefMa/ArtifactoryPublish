# A test project for ArtifactoryPublish
The [`jvm`](jvm/) sample contains a simple class called `JVMSample` with a 
static `helloWorld()` method.

The containing [build.gradle](jvm/build.gradle) is fully setup to publish 
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

#### Setup
The installed Artifactory can be found at [http://localhost:8081/artifactory/](http://localhost:8081/artifactory/).
After the fancy splashscreen your jump directly into the setup screen.

Set the **password** to **12341234**. Keep the rest at the default settings.

### Publish
After you have successfully setup the Artifactory you can finally publish
the library project by running:
```
./gradlew build artifactoryPublish -PartifactoryUser=admin -PartifactoryKey=12341234
```

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
    implementation "guru.stefma:jvm.sample:0.0.1"
}
```