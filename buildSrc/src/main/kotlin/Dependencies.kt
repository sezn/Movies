object Dependencies {

    const val android_core = "androidx.core:core-ktx:1.8.0"
    const val material = "com.google.android.material:material:1.6.1"
    const val material3 = "androidx.compose.material3:material3:${Versions.MATERIAL3}"
    const val coroutines ="org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"

    const val hilt = "com.google.dagger:hilt-android:2:44"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:2.44"
    const val hiltAndroidXCompiler = "androidx.hilt:hilt-compiler:1.0.0"

    const val gson = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val landscapist = "com.github.skydoves:landscapist-glide:1.5.3"

    const val firebase_bom = "com.google.firebase:firebase-bom:30.3.2"
    const val firebase_analytics = "com.google.firebase:firebase-analytics-ktx"

    const val compose_runtime = "androidx.compose.runtime:runtime:${Versions.COMPOSE}"
    const val compose_tooling = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
    const val appcompanist_navigation = "com.google.accompanist:accompanist-navigation-animation:${Versions.APP_COMPANIST}"
    const val appcompanist_pager = "com.google.accompanist:accompanist-pager:${Versions.APP_COMPANIST}"
    const val compose_tracing = "androidx.compose.runtime:runtime-tracing:1.0.0-alpha01"

    const val compose_paging = "androidx.paging:paging-compose:1.0.0-alpha16"
    const val compose_constraint = "androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha03"

    const val datastore = "androidx.datastore:datastore:1.0.0"
    const val datastore_preferences = "androidx.datastore:datastore-preferences:1.0.0"

    const val kotlin_collections_immutable = "org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5"
    const val kotlin_serialization_json = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2"

    const val test_junit = "junit:junit:4.13.2"
    const val test_junit_ext = "androidx.test.ext:junit:1.1.3"
    const val test_expresso = "androidx.test.espresso:espresso-core:3.4.0"
    const val test_compose = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"

}
