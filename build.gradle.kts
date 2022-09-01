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
    id("com.android.library") version Versions.GRADLE apply false
    id("org.jetbrains.kotlin.android") version Versions.KOTLIN apply false
    id("org.jetbrains.kotlin.jvm") version Versions.KOTLIN apply false
    id("com.google.dagger.hilt.android") version Versions.HILT apply false
}