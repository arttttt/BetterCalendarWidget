plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.arttttt.bettercalendarwidget"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.arttttt.bettercalendarwidget"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)

    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.android)

    implementation(libs.androidx.glance.appwidget)
    implementation(libs.androidx.glance.material3)

    implementation(libs.google.dagger2.lib)
    kapt(libs.google.dagger2.compiler)

    implementation(libs.arkivanov.essenty.lifecycle.lib)
    implementation(libs.arkivanov.essenty.lifecycle.coroutines)
    implementation(libs.arkivanov.essenty.backHandler)
    implementation(libs.arkivanov.essenty.stateKeeper)
    implementation(libs.arkivanov.essenty.instanceKeeper)
    implementation(libs.arkivanov.mviKotlin.core)
    implementation(libs.arkivanov.mviKotlin.coroutines)
    implementation(libs.arkivanov.mviKotlin.main)
    implementation(libs.arkivanov.mviKotlin.logging)
    implementation(libs.arkivanov.mviKotlin.timetravel)
}