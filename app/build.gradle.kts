plugins {
    id(Plugins.androidApplication)
    id(Plugins.jetAndroid)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.safeArgs)
    id(Plugins.androidHilt)
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

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }
    buildFeatures {
        compose = true
    }

}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core")) // TODO: see if can remove this dep?
    implementation(project(":auth-feature"))
    implementation(Dependencies.android_core)
    implementation(Dependencies.activity_compose)
    implementation(Dependencies.material)
//    implementation(Dependencies.material3)
    implementation(Dependencies.material_compose)
    implementation(Dependencies.view_model)

    // Hilt for DI
    implementation(Dependencies.hilt)
    implementation(Dependencies.hiltCompose)
    kapt(Dependencies.hiltCompiler)
    kapt(Dependencies.hiltAndroidXCompiler)

    // Jetpack Compose
    implementation(Dependencies.compose_foundation)
    implementation(Dependencies.compose_ui)
    implementation(Dependencies.compose_runtime)
    implementation(Dependencies.compose_navigation)
    implementation(Dependencies.compose_constraint)
    // Helpers for Compose (TODO: take care!)
    implementation(Dependencies.appcompanist_navigation)
    implementation(Dependencies.appcompanist_pager)

    implementation(platform(Dependencies.firebase_bom))
    implementation(Dependencies.firebase_analytics)

    implementation(Dependencies.landscapist)

    // TODO: voir ou le mettre
    // There is paging-common, which is a pure kotlin library that contains PagingSource and RemoteMediator among others.
    // That means that you can use paging-common in a pure kotlin module and in your repositories just fine, there is no android specific code in there
    implementation(Dependencies.compose_paging)
    implementation(Dependencies.room_runtime)

    testImplementation(Dependencies.test_junit)
    androidTestImplementation(Dependencies.test_junit_ext)
    androidTestImplementation(Dependencies.test_expresso)
    androidTestImplementation(Dependencies.test_compose)

    debugImplementation(Dependencies.compose_tooling)
    implementation(Dependencies.tooling_preview)
}