import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    kotlin("plugin.serialization")
    alias(libs.plugins.navigation.safe.args)
    alias(libs.plugins.ksp)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

val secretsFile: File = rootProject.file("secrets.properties")
val secretProperties = Properties()
if(secretsFile.exists()) {
    project.logger.info("Found secrets file")
    secretProperties.load(FileInputStream(secretsFile))
} else {
    project.logger.info("No Secrets file found, using system env.")
}

android {
    namespace = "com.cygni.tim.weatherexplore"
    compileSdk = 34
    buildToolsVersion = "34.0.0"

    defaultConfig {
        applicationId = "com.cygni.tim.weatherexplore"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file("../keystore.jks")
            storePassword = secretProperties["KEY_STORE_PASSWORD"] as? String ?: System.getenv("KEY_STORE_PASSWORD")
            keyAlias = secretProperties["ALIAS"] as? String ?: System.getenv("ALIAS")
            keyPassword = secretProperties["KEY_PASSWORD"] as? String ?: System.getenv("KEY_PASSWORD")
        }
    }


    buildTypes {
        getByName("release")  {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
        compose = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }

    @Suppress("UnstableApiUsage")
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    lint {
        abortOnError = false
        checkReleaseBuilds =  false
        xmlReport = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

secrets {
    propertiesFileName = "secrets.properties"

    defaultPropertiesFileName = "local.defaults.properties"

    ignoreList.add("keyToIgnore") // Ignore the key "keyToIgnore"
    ignoreList.add("sdk.*")       // Ignore all keys matching the regexp "sdk.*"
}

dependencies {

    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)


    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.coil.compose)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.core)
    ksp(libs.ksp)

    // Java language implementation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Kotlin
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.navigation.ui.ktx)

    // Feature module Support
    implementation(libs.androidx.navigation.dynamic.features.fragment)

    // Testing Navigation
    androidTestImplementation(libs.androidx.navigation.testing)

    // Reflection
    implementation(libs.kotlin.reflect)

    // Jetpack Compose Integration
    implementation(libs.androidx.navigation.compose)
    // Integration with activities
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.ui)

    implementation(libs.maps.google.android.maps)
    implementation(libs.maps.google.android.compose)
    implementation(libs.maps.utils)
    implementation(libs.maps.compose.utils)
    implementation(libs.maps.compose.widgets)

    implementation(libs.lifecycle.viewmodel.compose)
    // Constraint Layout
    implementation(libs.androidx.constraintlayout.compose)


    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.compose.ui.test)
    androidTestImplementation(libs.androidx.compose.ui.test)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test)
    debugImplementation(libs.androidx.compose.ui.manifest.test)
}