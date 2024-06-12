// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt.android) apply false
    kotlin("jvm").version(libs.versions.kotlinAndroid).apply(false)
    kotlin("plugin.serialization").version(libs.versions.kotlinAndroid).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.navigation.safe.args) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.kover) apply false
}

buildscript {
    dependencies {
        classpath(libs.secrets.gradle.plugin)
    }
}
