buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.2")
        classpath("com.google.gms:google-services:4.3.13")
    }
}

plugins {
    id("com.android.application") version Versions.GRADLE apply false
    id("com.android.library") version "7.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
    id("org.jetbrains.kotlin.jvm") version Versions.KOTLIN apply false
    id("com.google.dagger.hilt.android") version Versions.HILT apply false
    id("com.android.dynamic-feature") version "7.2.2" apply false
}