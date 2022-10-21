plugins {
    id("android.library")
    id("android.hilt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.szn.common"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:datastore"))
    implementation(project(":core:database"))
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.paging)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.ktx)

    implementation(Dependencies.gson)
    implementation(libs.androidx.dataStore.core)
    implementation("com.google.protobuf:protobuf-javalite:3.18.0")
}
