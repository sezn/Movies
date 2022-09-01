plugins {
    id(Plugins.androidApplication)
    id(Plugins.jet_android)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.safe_args)
    id(Plugins.androidHilt)
    id(Plugins.google_services)
}

android {
    compileSdk = Versions.ANDROID

    defaultConfig {
        applicationId = "com.szn.movies"
        minSdk = 21
        targetSdk = Versions.ANDROID
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.KOTLIN_COMPILER
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }

}

dependencies {
    implementation(project(":core"))
    implementation(Dependencies.android_core)
    implementation(Dependencies.app_compat)
    implementation(Dependencies.material)
//    implementation(Dependencies.material3)
    implementation(Dependencies.material_compose)

    // Hilt for DI
    implementation(Dependencies.hilt)
    implementation(Dependencies.hiltCompose)
    kapt(Dependencies.hiltCompiler)
    kapt(Dependencies.hiltAndroidXCompiler)

    // Jetpack Compose
    implementation(Dependencies.compose_foundation)
    implementation(Dependencies.compose_ui)
    implementation(Dependencies.compose_tooling)

    implementation(platform(Dependencies.firebase_bom))
    implementation(Dependencies.firebase_analytics)

    testImplementation(Dependencies.test_junit)
    androidTestImplementation(Dependencies.test_junit_ext)
    androidTestImplementation(Dependencies.test_expresso)
    androidTestImplementation(Dependencies.test_compose)
}