object Dependencies {

    const val android_core = "androidx.core:core-ktx:1.7.0"
    const val app_compat = "androidx.appcompat:appcompat:1.6.0-beta01"
    const val material = "com.google.android.material:material:1.6.1"
    const val material3 = "androidx.compose.material3:material3:${Versions.MATERIAL3}"
    const val material_compose ="androidx.compose.material:material:${Versions.COMPOSE}"

    const val hilt = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.HILT}"
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT_ANDROID}"
    const val hiltAndroidXCompiler = "androidx.hilt:hilt-compiler:${Versions.HILT_NAVIGATION}"
    const val hiltCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val okhttp_interceptor = "com.squareup.okhttp3:logging-interceptor:4.9.3"
    const val gson = "com.squareup.retrofit2:converter-gson:2.9.0"

    const val landscapist = "com.github.skydoves:landscapist-glide:1.5.3"

    const val firebase_bom = "com.google.firebase:firebase-bom:30.3.2"
    const val firebase_analytics = "com.google.firebase:firebase-analytics-ktx"

    const val compose_ui = "androidx.compose.ui:ui:1.2.1"
    const val compose_tooling = "androidx.compose.ui:ui-tooling:1.2.1"
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    const val compose_foundation = "androidx.compose.foundation:foundation:1.2.1"

    const val test_junit = "junit:junit:4.13.2"
    const val test_junit_ext = "androidx.test.ext:junit:1.1.3"
    const val test_expresso = "androidx.test.espresso:espresso-core:3.4.0"
    const val test_compose = "androidx.compose.ui:ui-test-junit4:1.2.1"

}