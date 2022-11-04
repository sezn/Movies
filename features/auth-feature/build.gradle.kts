plugins {
    id("android.feature")
    id("android.library.compose")
}

android {
    namespace = "com.szn.movie.auth"
}

dependencies {

    implementation(project(":core:common"))
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.iconsExtended)

    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.customview.poolingcontainer)
}