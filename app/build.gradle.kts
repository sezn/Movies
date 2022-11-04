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
        versionCode = 2
        versionName = "1.1"
        base.archivesName.set("Movies-${android.defaultConfig.versionName}(${android.defaultConfig.versionCode})")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    namespace = "com.szn.movies"
}

dependencies {
    implementation(project(":core:common"))
//    TODO: remove
    implementation(project(":core:network"))
    implementation(project(":features:auth-feature"))
    implementation(project(":features:account-feature"))

    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.google.material)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.hilt.navigation.compose)

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

    testImplementation(Dependencies.test_junit)
    androidTestImplementation(Dependencies.test_junit_ext)
    androidTestImplementation(Dependencies.test_expresso)
    androidTestImplementation(Dependencies.test_compose)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0")
    debugImplementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.0-alpha02")
}