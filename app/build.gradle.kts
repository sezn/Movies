plugins {
    id("android.application.compose")
//    id(Plugins.safeArgs)
    id("android.hilt")
}

android {
    compileSdk = Versions.ANDROID

    defaultConfig {
        applicationId = "com.szn.movies"
        minSdk = 21
        targetSdk = Versions.ANDROID
        versionCode = 1
        versionName = "1.0"
        base.archivesName.set("Movies-${android.defaultConfig.versionName}(${android.defaultConfig.versionCode})")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    namespace = "com.szn.movies"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":cored"))
    implementation(project(":core:common"))
//    TODO: remove
    implementation(project(":core:network"))
    implementation(project(":features:auth-feature"))
    implementation(project(":features:account-feature"))
    implementation(Dependencies.android_core)
    implementation(Dependencies.activity_compose)
    implementation(Dependencies.material)
    implementation(Dependencies.material3)
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
    implementation(Dependencies.compose_tracing)
    // Helpers for Compose (TODO: take care!)
    implementation(Dependencies.appcompanist_navigation)
    implementation(Dependencies.appcompanist_pager)
    implementation(Dependencies.datastore)

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
    debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0")
    debugImplementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.0-alpha02")
}