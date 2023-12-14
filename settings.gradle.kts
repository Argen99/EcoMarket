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

rootProject.name = "EcoMarket"
include(":app")
include(":core")
include(":features")
include(":features:main")
include(":features:basket")
include(":features:story")
include(":features:info")
include(":core:ui")
include(":data")
