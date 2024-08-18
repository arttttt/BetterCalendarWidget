plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.arttttt.core.arch"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"

        freeCompilerArgs = freeCompilerArgs + listOf("-Xcontext-receivers")

        options.optIn.addAll(
            "kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}

dependencies {
    api(libs.arkivanov.decompose.lib)
    api(libs.arkivanov.decompose.compose)
    api(libs.arkivanov.essenty.lifecycle.lib)
    api(libs.arkivanov.essenty.lifecycle.coroutines)
    api(libs.arkivanov.essenty.backHandler)
    api(libs.arkivanov.essenty.instanceKeeper)
    api(libs.arkivanov.essenty.stateKeeper)

    api(libs.arkivanov.mviKotlin.core)
    api(libs.arkivanov.mviKotlin.main)
    api(libs.arkivanov.mviKotlin.logging)
    api(libs.arkivanov.mviKotlin.timetravel)
    api(libs.arkivanov.mviKotlin.coroutines)

    api(libs.kotlin.coroutines.core)

    api(libs.kotlin.atomicfu)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)

    implementation(libs.google.dagger2.lib)
}