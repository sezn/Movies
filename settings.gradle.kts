pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "MoviesDb"
include(":app", ":domain", ":cored")
include(":core:datastore")
include(":core:common")
include(":core:network")

include(":features:auth-feature")
include(":features:account-feature")

include(":core:database")
