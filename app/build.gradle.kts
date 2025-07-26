import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

// ==============================================================================
// 1. Safely Load API Keys from local.properties using 'lazy'
//    - This ensures the 'properties' object is initialized exactly once,
//      and is guaranteed to be non-null when accessed.
// ==============================================================================
// Top of your app/build.gradle.kts




// Define the lazy property at the top level of the script,
// or ensure it's accessed correctly if defined within a specific scope.
val loadedAppProperties: Properties by lazy {
    val props = Properties() // Uses java.util.Properties// Initialize Properties inside the lazy block
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
    props // Return the Properties object
}
android {
    namespace = "com.example.personalorganiser"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.personalorganiser"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Access the lazy property
        buildConfigField("String", "WEATHER_API_KEY", "\"${loadedAppProperties.getProperty("weatherApiKey", "")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            // It's good practice to enable buildConfig for debug builds
            // if you are using buildConfigFields
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
        // Ensure buildConfig is enabled if you are defining buildConfigFields
        // This is often implicitly enabled but can be explicit.
        // buildConfig = true // Already set in debug, consider if needed for release
    }
}

dependencies {
    // Standard Android UI Libraries
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // ViewModel and LiveData (for Architecture Components)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.0")

    // Kotlin Coroutines (for asynchronous operations)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

    // Retrofit (Type-safe HTTP client for Android and Java)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Converter for JSON serialization/deserialization with Gson
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp Logging Interceptor (for logging network requests/responses in Logcat)
    // IMPORTANT: Only use in debug builds or disable in release builds due to sensitive data logging.
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation(libs.androidx.room.compiler)

    // Unit Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

}