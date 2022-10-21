plugins {
    id("android.feature")
    id("android.library.compose")
}

android {
    namespace = "com.szn.movie.auth"
}

dependencies {

    implementation(project(":cored"))
    implementation(Dependencies.material_compose)
    implementation(Dependencies.material3)
    implementation(Dependencies.compose_extended)

    debugImplementation(Dependencies.compose_tooling)
    implementation(Dependencies.tooling_preview)

    debugImplementation("androidx.customview:customview:1.2.0-alpha01")
    debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0-alpha01")
}