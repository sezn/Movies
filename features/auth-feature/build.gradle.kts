plugins {
    id("android.feature")
    id("android.library.compose")
}

android {
    namespace = "com.szn.movie.auth"
}

dependencies {

    implementation(project(":cored"))
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.iconsExtended)

    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)

    debugImplementation("androidx.customview:customview:1.2.0-alpha01")
    debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0-alpha01")
}