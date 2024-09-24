plugins {
    alias(libs.plugins.android.application)
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile = file("/home/iap/StudioProjects/customer3/key")
            storePassword = "123joni"
            keyAlias = "coopkey"
            keyPassword = "123joni"
        }
        create("release") {
            storeFile = file("/home/iap/StudioProjects/customer3/key")
            storePassword = "123joni"
            keyAlias = "coopkey"
            keyPassword = "123joni"
        }
    }
    namespace = "com.hira.coopay.customer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hira.coopay.customer"
        minSdk = 24
        targetSdk = 34
        versionCode = 34
        versionName = "1.3.4"
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
    buildFeatures {
        viewBinding = true
    }
    buildToolsVersion = "35.0.0"
    ndkVersion = "27.1.12297006"
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.play.services.ads.lite)
    implementation(libs.firebase.messaging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}