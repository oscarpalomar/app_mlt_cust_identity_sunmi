plugins {
    id("com.android.library") version "8.13.2"
    id("org.jetbrains.kotlin.android") version "2.3.20"
}

group = "com.neogrup.app_mlt_cust_identity_sunmi"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://storage.googleapis.com/download.flutter.io") }
    }
}

android {
    namespace = "com.neogrup.app_mlt_cust_identity_sunmi"

    compileSdk = 36

    compileOptions {
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    sourceSets {
        getByName("main") {
            java.srcDirs("src/main/kotlin")
        }
        getByName("test") {
            java.srcDirs("src/test/kotlin")
        }
    }

    defaultConfig {
        minSdk = 24
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            all {
                it.useJUnitPlatform()

                it.outputs.upToDateWhen { false }

                it.testLogging {
                    events("passed", "skipped", "failed", "standardOut", "standardError")
                    showStandardStreams = true
                }
            }
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.annotation:annotation:1.11.0")
    compileOnly("io.flutter:flutter_embedding_debug:1.0.0-e85ea0e79c6d126c19f29518823d666d92bbae40")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.mockito:mockito-core:5.0.0")
    api("com.sunmi:printerlibrary:1.0.24")
    implementation(kotlin("stdlib-jdk8"))
    // implementation(files("/Users/oscar/DEVELOPMENT/APPLICATIONS/flutter/bin/cache/artifacts/engine/android-x64-release/flutter.jar"))
}
repositories {
    mavenCentral()
}