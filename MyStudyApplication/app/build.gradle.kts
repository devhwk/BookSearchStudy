plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.secrets.gradle)
    alias(libs.plugins.safe.args)
    alias(libs.plugins.hilt)
    id("kotlin-parcelize")
    alias(libs.plugins.ksp)
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

//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.example.mystudyapplication.HiltTestRunner"
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

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
        animationsDisabled = true
    }
}

dependencies {
    // default
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)
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

    // room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)

    // serialization
    implementation(libs.serialization.json)

    // recyclerview
    implementation(libs.recyclerview)

    // dataStore
    implementation(libs.datastore)

    //paging
    implementation(libs.paging)

    // workManager
    implementation(libs.workmanager)

    //hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.ex.work)
    ksp(libs.hilt.ex.compiler)

    // test
    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.runner)
    testImplementation(libs.androidx.junit)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.robo)
    testImplementation(libs.test.coroutine)

    androidTestImplementation(libs.truth)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.espresso.contrib)
    androidTestImplementation(libs.test.coroutine)
    androidTestImplementation(libs.hilt.test)
    androidTestImplementation(libs.hamcrest)
    kspAndroidTest(libs.hilt.compiler)
}