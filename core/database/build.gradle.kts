plugins {
    id("android.library")
    id("android.hilt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.szn.database"
}

dependencies {
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    implementation(libs.room.paging)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.paging)
    implementation("com.google.code.gson:gson:2.9.1")
    kapt(libs.room.compiler)
    testImplementation(libs.room.test)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
}