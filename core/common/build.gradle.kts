plugins {
    id("android.library")
    id("android.hilt")

}

android {
    namespace = "com.szn.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(project(":domain"))
    implementation(project(":core:network"))
    implementation(project(":core:datastore"))
//    implementation(project(":core:database"))

    implementation("androidx.datastore:datastore-core:1.0.0")
    implementation("com.google.protobuf:protobuf-javalite:3.18.0")
}
