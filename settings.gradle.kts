pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "NewsProjectDark"
include(":app")
include(":libraries:framework")
include(":libraries:components")
include(":data:model")
include(":data:local")
include(":data:remote")
include(":data:repository")
include(":domain")
