@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    kotlin("kapt")
    id(libs.plugins.com.google.dagger.hilt.android.get().pluginId)
}

android {
    namespace = "com.example.ecomarket"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ecomarket"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    //Projects
    implementation(project(":features:main"))
    implementation(project(":features:basket"))
    implementation(project(":features:story"))
    implementation(project(":features:info"))

    implementation(libs.core.ktx)

    //Bundles
    implementation(libs.bundles.ui)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.navigation)

    //Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //Hilt
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.android.compiler)
}