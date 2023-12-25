@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.agp.library)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
    id(libs.plugins.hilt.android.get().pluginId)
}

android {
    namespace = "com.example.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        buildConfigField("String", "BASE_URL", "\"https://neobook.online/ecobak/\"")
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
        buildConfig = true
    }
}

dependencies {
    implementation(project(":features:main"))
    //Core
    implementation(libs.androidx.core)
    //Bundles
    implementation(libs.bundles.retrofit)
    //Test
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.ext.junit)
    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.android.extensions)
    kapt(libs.hilt.android.extensions.processor)
    //Coroutines
    implementation(libs.coroutines.android)
    //Paging
    implementation(libs.paging.runtime)
}