
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
}

dependencies {
    implementation(project(":core"))
    implementation(project(":auth-feature"))
    implementation(Dependencies.android_core)

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
}