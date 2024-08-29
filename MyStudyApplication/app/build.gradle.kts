plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.secrets.gradle)
    alias(libs.plugins.safe.args)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.mystudyapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mystudyapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    // default
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // connection
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.gson)
    implementation(libs.okhttp3)

    // viewmodel
    implementation(libs.lifecylcle.viewmodel)

    // coroutine
    implementation(libs.coroutine)

    // coil
    implementation(libs.coil)

    // navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}