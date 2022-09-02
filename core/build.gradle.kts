plugins {
    id(Plugins.androidLibrary)
    id(Plugins.jetAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.androidHilt)
    id(Plugins.kotlinParcelize)
}

android {
    compileSdk = Versions.ANDROID

    defaultConfig {
        minSdk = 21
        targetSdk = Versions.ANDROID

        buildConfigField("String", "MOVIES_BASE_URL", "\"https://api.themoviedb.org/\"")
        buildConfigField("String", "IMAGE_BASE", "\"https://image.tmdb.org/t/p/w500\"")
        buildConfigField("String", "API_KEY", getApiKey())

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(Dependencies.android_core)
    testImplementation(Dependencies.test_junit)

    // Hilt for DI
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)
    kapt(Dependencies.hiltAndroidXCompiler)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.okhttp_interceptor)
    implementation(Dependencies.gson)

    implementation(Dependencies.room_runtime)
    implementation(Dependencies.room_ktx)
    kapt(Dependencies.room_compiler)
    testImplementation(Dependencies.room_testing)


    androidTestImplementation(Dependencies.test_junit_ext)
    androidTestImplementation(Dependencies.test_expresso)
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
        return System.getenv("api_key")
    }

    return items["api_key"]!!
}