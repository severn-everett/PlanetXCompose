import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    kotlin("plugin.compose")
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
}

group = "com.severett"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvmToolchain(21)

    jvm {}
    js(IR) {
        browser {
            testTask {
                testLogging.showStandardStreams = true
                useKarma {
                    useChromeHeadless()
                    useFirefox()
                }
            }
        }
        binaries.executable()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:atomicfu:0.24.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")
                implementation("org.jetbrains.kotlinx:kotlinx-html:0.11.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
            }
        }
        val jvmMain by getting {
            dependencies {
                val composeVersion = extra["compose.version"] as String
                implementation(compose.desktop.currentOs) {
                    exclude(group = "org.jetbrains.compose.material", module = "material")
                }
                implementation("org.jetbrains.compose.material3:material3-desktop:$composeVersion")
            }
        }
        val jvmTest by getting {
            val junitVersion: String by project
            dependencies {
                implementation("org.jctools:jctools-core:4.0.1")
                implementation("org.jetbrains.kotlinx:lincheck:2.26")
                implementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
                runtimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(compose.runtime)
                implementation(npm("@js-joda/timezone", "2.21.0"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

compose {
    desktop {
        application {
            mainClass = "com.severett.planetxcompose.jvm.MainKt"
            nativeDistributions {
                targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                packageName = "PlanetXCompose"
                packageVersion = "1.0.0"
                macOS {
                    // iconFile.set(project.file("src/commonMain/resources/icons/app/an_icon.icns"))
                }
            }
        }
    }
}

tasks.getByName<Test>("jvmTest") {
    useJUnitPlatform()
    jvmArgs = listOf(
        // Arguments that are required for working with classes from
        // the java.util package
        "--add-opens",
        "java.base/jdk.internal.misc=ALL-UNNAMED",
        "--add-exports",
        "java.base/jdk.internal.util=ALL-UNNAMED",
        // Arguments that are to be uncommented for LockCoarseningTest
        // "-XX:+UnlockDiagnosticVMOptions",
        // "-XX:+StressLCM",
        // "-XX:+StressGCM",
        // "-XX:-EliminateLocks",
    )
}
