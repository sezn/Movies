plugins {
    id("android.library")
    id("android.hilt")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("kotlin-parcelize")
}

android {
    namespace = "com.szn.network"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "MOVIES_BASE_URL", "\"https://api.themoviedb.org/\"")
        buildConfigField("String", "IMAGE_BASE", "\"https://image.tmdb.org/t/p/w500\"")
        buildConfigField("String", "API_KEY", getApiKey())
        buildConfigField("String", "GRAVATAR_URL", "\"https://gravatar.com/avatar/\"")
    }

}

dependencies {
    implementation(libs.retrofit.core)
    implementation(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation(Dependencies.gson)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}


/**
 * from https://stackoverflow.com/questions/59052655/kotlin-dsl-retrieving-keys-from-other-file
 * api.properties is ignored for git
 *
 * if you want to test, you have just to create a  api.properties file with
 * api_key="YOUR_API_KEY"
 *
 * Or set it in local.properties and change FILENAME
 */
fun getApiKey(): String {
    val items = HashMap<String, String>()
    val FILE_NAME = "api.properties"
    val fl = rootProject.file(FILE_NAME)

    if(fl.exists()){
        fl.forEachLine {
            items[it.split("=")[0]] = it.split("=")[1]
        }
    } else {
        // Defined api_key in Gitlab variables
        val key = System.getProperty("api_key")
        if(key != null) return key
        val k = "${System.getenv()["api_key"]}"
        if(k != null) return k
        return System.getenv("api_key")
    }

    return items["api_key"]!!
}