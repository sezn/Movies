object Dependencies {

    const val android_core = "androidx.core:core-ktx:1.8.0"
    const val activity_compose = "androidx.activity:activity-compose:1.5.1"
    const val material = "com.google.android.material:material:1.6.1"
    const val material3 = "androidx.compose.material3:material3:${Versions.MATERIAL3}"
    const val material_compose ="androidx.compose.material:material:${Versions.COMPOSE}"
    const val view_model ="androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
    const val coroutines ="org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"

    const val hilt = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.HILT}"
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.HILT_ANDROID}"
    const val hiltAndroidXCompiler = "androidx.hilt:hilt-compiler:${Versions.HILT_NAVIGATION}"
    const val hiltCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val okhttp_interceptor = "com.squareup.okhttp3:logging-interceptor:4.9.3"
    const val gson = "com.squareup.retrofit2:converter-gson:2.9.0"

    const val room_runtime = "androidx.room:room-runtime:${Versions.ROOM}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.ROOM}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.ROOM}"
    const val room_paging = "androidx.room:room-paging:${Versions.ROOM}"
    const val room_testing = "androidx.room:room-testing:${Versions.ROOM}"

    const val landscapist = "com.github.skydoves:landscapist-glide:1.5.3"

    const val firebase_bom = "com.google.firebase:firebase-bom:30.3.2"
    const val firebase_analytics = "com.google.firebase:firebase-analytics-ktx"

    const val compose_ui = "androidx.compose.ui:ui:${Versions.COMPOSE}"
    const val compose_runtime = "androidx.compose.runtime:runtime:${Versions.COMPOSE}"
    const val compose_tooling = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
    const val tooling_preview = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}"
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    const val compose_foundation = "androidx.compose.foundation:foundation:${Versions.COMPOSE}"
    const val compose_navigation = "androidx.navigation:navigation-compose:2.5.1"
    const val compose_extended = "androidx.compose.material:material-icons-extended:${Versions.COMPOSE}"
    const val appcompanist_navigation = "com.google.accompanist:accompanist-navigation-animation:${Versions.APP_COMPANIST}"
    const val appcompanist_pager = "com.google.accompanist:accompanist-pager:${Versions.APP_COMPANIST}"

    const val compose_paging = "androidx.paging:paging-compose:1.0.0-alpha16"
    const val compose_constraint = "androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha03"

    const val datastore = "androidx.datastore:datastore-preferences:1.0.0"

    const val test_junit = "junit:junit:4.13.2"
    const val test_junit_ext = "androidx.test.ext:junit:1.1.3"
    const val test_expresso = "androidx.test.espresso:espresso-core:3.4.0"
    const val test_compose = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"

}