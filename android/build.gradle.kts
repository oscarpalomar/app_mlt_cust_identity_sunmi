plugins {
    id("com.android.library") // version "8.13.2"
    id("org.jetbrains.kotlin.android") version "2.3.20"
}

group = "com.neogrup.app_mlt_cust_identity_sunmi"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        google()
        mavenCentral()
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
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.mockito:mockito-core:5.0.0")
    api("com.sunmi:printerlibrary:1.0.24")
    implementation(kotlin("stdlib-jdk8"))
}
repositories {
    mavenCentral()
}