plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    // Your existing android block content
    namespace = "com.example.personalorganiser"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.personalorganiser"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "Weather", "\"${properties.getProperty("weatherApiKey")}\"")
        buildConfigField("String", "EchoPost", "\"${properties.getProperty("weatherApiKey")}\"")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        // Core AndroidX (Java)
        implementation("androidx.appcompat:appcompat:1.7.0")
        implementation("com.google.android.material:material:1.12.0")
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")
        implementation("androidx.activity:activity:1.9.0") // Needed for ActivityResultLauncher in Java
        implementation("androidx.fragment:fragment:1.8.0") // Needed for fragment support in Java

        // Kotlin Standard Library (important for mixed projects)
        implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0") // Use your project's Kotlin version

        // Kotlin Coroutines (still useful even if main logic is Java, for ViewModelScope)
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
        // ViewModel and LiveData (for easier data management in UI)
        implementation("androidx.lifecycle:lifecycle-viewmodel:2.8.0") // Java version
        implementation("androidx.lifecycle:lifecycle-livedata:2.8.0")     // Java version

        // Retrofit
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        // GSON Converter (for JSON to Java/Kotlin object conversion)
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")
        // OkHttp Logging Interceptor (highly recommended for debugging network requests)
        implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation(libs.androidx.navigation.fragment)

    // Testing dependencies
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.2.1")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    }


private fun MutableMap<String, *>.getProperty(string: String): String {


}
