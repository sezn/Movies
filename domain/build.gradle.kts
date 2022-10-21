plugins {
    id("android.library")
    id(Plugins.kotlinParcelize)
}

android {
    defaultConfig {
        buildConfigField("String", "IMAGE_BASE", "\"https://image.tmdb.org/t/p/w500\"")
    }
    namespace = "com.szn.movies.domain"
}

dependencies {
    implementation(Dependencies.android_core)
    implementation(Dependencies.coroutines)
    testImplementation(Dependencies.test_junit)
    androidTestImplementation(Dependencies.test_junit_ext)
}