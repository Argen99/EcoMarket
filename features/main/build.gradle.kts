@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    kotlin("kapt")
    id(libs.plugins.com.google.dagger.hilt.android.get().pluginId)
}

android {
    namespace = "com.example.main"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:ui"))
    api(project(":core:common"))

    implementation(libs.androidx.core)
    //Bundles
    implementation(libs.bundles.ui)
    implementation(libs.bundles.navigation)
    //Coroutines
    implementation(libs.kotlinx.coroutines)
    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}