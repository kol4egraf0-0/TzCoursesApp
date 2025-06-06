plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.presentation"
    compileSdk = 35

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        minSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation("androidx.activity:activity-ktx:1.8.0")
    implementation("com.github.ismaeldivita:chip-navigation-bar:1.4.0")
    implementation(libs.glide)
    implementation(project(":domain"))
    testImplementation(libs.junit)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}