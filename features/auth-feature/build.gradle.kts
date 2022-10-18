plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.androidHilt)
    id(Plugins.kotlinKapt)
}

android {
    compileSdk = Versions.ANDROID

    defaultConfig {
        minSdk = 21
        targetSdk = Versions.ANDROID
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
    }
    buildFeatures {
        compose = true
    }
    namespace = "com.szn.movie.auth"
}

dependencies {
    implementation(project(":core"))
    implementation(Dependencies.android_core)
    implementation(Dependencies.view_model)

    implementation(Dependencies.compose_foundation)
    implementation(Dependencies.compose_ui)
    implementation(Dependencies.compose_navigation)
    implementation(Dependencies.compose_runtime)
    implementation(Dependencies.material_compose)
    implementation(Dependencies.material3)
    implementation(Dependencies.compose_extended)
    implementation(Dependencies.datastore)

    implementation(Dependencies.hilt)
    implementation(Dependencies.hiltCompose)
    kapt(Dependencies.hiltCompiler)
    kapt(Dependencies.hiltAndroidXCompiler)

    testImplementation(Dependencies.test_junit)
    androidTestImplementation(Dependencies.test_junit_ext)
    androidTestImplementation(Dependencies.test_expresso)

    debugImplementation(Dependencies.compose_tooling)
    implementation(Dependencies.tooling_preview)

    debugImplementation("androidx.customview:customview:1.2.0-alpha01")
    debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0-alpha01")
}