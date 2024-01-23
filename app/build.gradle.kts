plugins {
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("com.android.application")
    id("kotlin-android")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.cygni.tim.weatherexplore"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cygni.tim.weatherexplore"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("androidx.compose.material:material-icons-core:1.5.4")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("com.google.dagger:hilt-android:2.50")
    ksp("com.google.dagger:hilt-android-compiler:2.50")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation("io.coil-kt:coil-compose:1.4.0")
    implementation("com.google.accompanist:accompanist-flowlayout:0.32.0")
    implementation("io.github.raamcosta.compose-destinations:core:1.9.62")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.9.62")

    // Java language implementation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    implementation("androidx.compose.foundation:foundation-layout:1.5.4")

    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

    // Feature module Support
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.7.6")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:2.7.6")

    // Reflection
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.20")

    // Jetpack Compose Integration
    implementation("androidx.navigation:navigation-compose:2.7.6")

    // Integration with activities
    implementation("androidx.activity:activity-compose:1.8.2")
    // Compose Material Design
    implementation("androidx.compose.material:material:1.5.4")
    // Compose Material Design 3
    implementation("androidx.compose.material3:material3:1.1.2")
    // Animations
    implementation("androidx.compose.animation:animation:1.5.4")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.5.4")
    // Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    // Constraint Layout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}