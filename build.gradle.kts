import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
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
    jvm {
        jvmToolchain(19)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs) {
                    exclude(group = "org.jetbrains.compose.material", module = "material")
                }
                implementation("org.jetbrains.compose.material3:material3-desktop:1.4.0")
                implementation("org.jetbrains.kotlinx:atomicfu:0.20.2")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.8.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "com.severett.planetxcompose.jvm.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "PlanetXCompose"
            packageVersion = "1.0.0"
            macOS {
                iconFile.set(project.file("src/jvmMain/resources/app/apium_dark.icns"))
            }
        }
    }
}
