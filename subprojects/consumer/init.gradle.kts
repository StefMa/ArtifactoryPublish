class ProjectListener : ProjectEvaluationListener {
    var currentVersion = ""

    override fun beforeEvaluate(project: Project) {
        // This will publish the library to the local maven before
        if (project == project.rootProject) {
            project.exec {
                workingDir("../..")
                commandLine("./gradlew", "publishToMavenLocal")

                // This get the current version o the plugin
                // and set it to the rootProject as version
                currentVersion = File("../../build.gradle.kts")
                        .readLines()
                        .find { it.contains("version =") }!!
                        .split("= ")[1]
                        .replace("\"", "")
                project.rootProject.version = currentVersion
            }
        }
    }

    override fun afterEvaluate(project: Project, state: ProjectState) {
        // do nothing
    }
}

gradle.addProjectEvaluationListener(ProjectListener())