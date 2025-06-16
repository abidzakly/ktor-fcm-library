plugins {
    alias(libs.plugins.android.library) // replace with your AGP version
    id("org.jetbrains.kotlin.android") // replace with your Kotlin version
    id("kotlinx-serialization")
    id("maven-publish")
}

android {
    namespace = "com.abidzakly.ktorfcm"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.auth)
    implementation(libs.ktor.client.logging)

    implementation(libs.androidx.core.ktx.v1120)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.abidzakly"
                artifactId = "battery-check-aar"
                version = "1.7"
            }
        }
    }
}
