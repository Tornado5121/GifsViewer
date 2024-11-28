plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.zhadko.gifyviewer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zhadko.gifyviewer"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        buildConfigField(
            "String",
            "BASE_BACKEND_URL",
            "\"https://api.giphy.com/\""
        )

        buildConfigField(
            "String",
            "API_KEY",
            "\"cry5VL5RmQx867VjbcyUALGWpKsglGaw\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
        buildConfig = true
    }
}

dependencies {
    implementation(libs.core)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraint)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle)
    implementation(libs.glide)

    //JetpackNavigation
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    //Koin
    implementation(libs.koin.android)

    //Retrofit
    implementation(libs.converter.gson)
    implementation(libs.retrofit)

    //Pager3
    implementation(libs.paging.runtime)
}