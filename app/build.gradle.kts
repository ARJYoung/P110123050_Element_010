// app/build.gradle.kts
@file:Suppress("DEPRECATION")

import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.ir.types.AbstractIrTypeSubstitutor.Empty.substitute
import org.jetbrains.kotlin.ir.types.IrType
import java.util.Properties
import java.io.FileInputStream


plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}


val loadedAppProperties: Properties by lazy {
    val props = Properties()
    val localPropertiesFile = project.rootProject.file("local.properties")

    if (localPropertiesFile.exists()) {
        try {
            FileInputStream(localPropertiesFile).use { input ->
                props.load(input)
            }
        } catch (e: Exception) {
            System.err.println("ERROR: Failed to load local.properties from ${localPropertiesFile.absolutePath}: ${e.message}. API keys will be empty.")
        }
    } else {
        println("WARNING: local.properties file not found at ${localPropertiesFile.absolutePath}. API keys will be empty.")
    }
    props
}

android {
    namespace = "com.example.personalorganiser"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.personalorganiser"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "WEATHER_API_KEY", "\"${loadedAppProperties.getProperty("weatherApiKey", "")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            android.buildFeatures.buildConfig = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // === FIX FOR DUPLICATE ANNOTATIONS ERROR ===
    // Removed bad imports and corrected the syntax here.
    configurations.all {
        resolutionStrategy {
            force("org.jetbrains:annotations:23.0.0")
            eachDependency {
                if (requested.group == "com.intellij" && requested.name == "annotations") {
                    useTarget("org.jetbrains:annotations:23.0.0")
                    because("IntelliJ annotations are replaced by JetBrains annotations to avoid conflicts.")
                }
            }
        }
    }

    // ==========================================

    // --- Standard Android UI Libraries ---
    // Cleaned up duplicate dependencies
    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")

    // ViewModel and LiveData (for Architecture Components)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.9.2")

    // Retrofit (Type-safe HTTP client for Android and Java)
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    // Converter for JSON serialization/deserialization with Gson
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    // OkHttp Logging Interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:5.1.0")
    implementation(libs.navigation.fragment)

    // The 'libs.androidx.room.compiler' is not a standard dependency.
    // Assuming this is for your own use, but it looks incorrect.
    // If you need Room, it's typically 'kapt("androidx.room:room-compiler:2.6.1")'
    // I am commenting it out for now as it will likely cause more issues.
    // implementation(libs.androidx.room.compiler)

    // Unit Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}