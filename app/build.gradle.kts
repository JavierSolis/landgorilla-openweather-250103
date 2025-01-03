plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "landgorilla.javiersolis.i020124.openweather"
    compileSdk = 34

    defaultConfig {
        applicationId = "landgorilla.javiersolis.i020124.openweather"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "0.0.1-beta"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_KEY", "\"${property("OPEN_WEATHER_KEY")}\"")
        buildConfigField("String", "BASE_URL", "\"https://api.openweathermap.org/data/2.5/\"")

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        viewBinding = true
        buildConfig = true
    }
}

val koin_version = "3.1.2"
val mockkVersion = "1.13.14"

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.recyclerview)
    implementation(libs.material)
    implementation(libs.androidx.junit.ktx)
    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //region injection
    implementation(libs.koin.android)
    implementation(libs.koin.android.compat)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    //endregion injection

    //region network
    //retrofit
    implementation(libs.retrofit)
    implementation("com.squareup.okhttp3:logging-interceptor:4.4.0")
    //JSON
    implementation("com.squareup.retrofit2:converter-moshi:2.8.1")
    implementation("com.google.code.gson:gson:2.8.8")

    //implementation Libs.moshi
    //kapt Kapt.moshi

    //kapt Libs.moshi_codegen
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.5.0")
    //endregion network

    //region persistence
    implementation("androidx.room:room-runtime:2.4.2")
    //endregion persitence

    //region test
    testImplementation(libs.junit)

    androidTestImplementation("androidx.test:runner:1.6.1")

    /*
    androidTestImplementation ("org.mockito:mockito-core:4.8.0")
    androidTestImplementation ("org.mockito:mockito-inline:4.8.0")
    androidTestImplementation ("org.mockito:mockito-android:4.8.0")
    androidTestImplementation ("org.mockito.kotlin:mockito-kotlin:4.8.0")
    */
    androidTestImplementation("io.mockk:mockk-android:${mockkVersion}")
    androidTestImplementation("io.mockk:mockk-agent:${mockkVersion}")

    //androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    //androidTestImplementation("androidx.test.ext:junit:1.1.5")
    //androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    //testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    //endregion test
}