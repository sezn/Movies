plugins {
    id("android.feature")
    id("android.library.compose")
}
android {
    namespace = "com.szn.movies.account"
}
dependencies {
    implementation(project(":cored"))
}

