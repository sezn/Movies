plugins {
    id(Plugins.androidApplication)
    id(Plugins.jet_android)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinParcelize)
    id(Plugins.safe_args)
    id(Plugins.androidHilt)
}

android {
    compileSdk = Versions.ANDROID

    defaultConfig {
        applicationId = "com.szn.movies"
        minSdk = 21
        targetSdk = Versions.ANDROID
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "API_KEY", getApiKey())

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.KOTLIN_COMPILER
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }

}

dependencies {

    implementation(Dependencies.android_core)
    implementation(Dependencies.app_compat)
    implementation(Dependencies.material)

    // Hilt for DI
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)
    implementation(Dependencies.hiltCompose)
    kapt(Dependencies.hiltAndroidXCompiler)

    testImplementation(Dependencies.test_junit)
    androidTestImplementation(Dependencies.test_junit_ext)
    androidTestImplementation(Dependencies.test_expresso)
}

/**
 * from https://stackoverflow.com/questions/59052655/kotlin-dsl-retrieving-keys-from-other-file
 * api.properties is ignored for git
 *
 * if you want to test, you have just to create a  api.properties file with
 * api_key="YOUR_API_KEY"
 */
fun getApiKey(): String {
    val items = HashMap<String, String>()

    val fl = rootProject.file("api.properties")

    (fl.exists())?.let {
        fl.forEachLine {
            items[it.split("=")[0]] = it.split("=")[1]
        }
    }

    return items["api_key"]!!
}