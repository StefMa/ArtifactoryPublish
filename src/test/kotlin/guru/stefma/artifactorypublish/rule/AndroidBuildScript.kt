fun appendAndroidExtension(): String = """
    android {
        compileSdkVersion 26
        buildToolsVersion "26.0.2"
        defaultConfig {
            minSdkVersion 16
            versionCode 1
            versionName "0.0.1"
        }
    }

    // Disable lint. No need to run it...
    tasks.lint.enabled = false

    repositories {
        jcenter()
        google()
    }
""".trimIndent()