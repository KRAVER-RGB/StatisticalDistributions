import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
}

group = "com.github.KRAVER-RGB"
version = "1.0.0"

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["kotlin"])

            groupId = "com.github.KRAVER-RGB"
            artifactId = "StatisticalDistributions"
            version = "1.0.0"

            pom {
                name.set("StatisticalDistributions")
                description.set("Librería multiplataforma para distribuciones estadísticas")
                url.set("https://github.com/KRAVER-RGB/StatisticalDistributions")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/KRAVER-RGB/StatisticalDistributions.git")
                    developerConnection.set("scm:git:ssh://github.com:KRAVER-RGB/StatisticalDistributions.git")
                    url.set("https://github.com/KRAVER-RGB/StatisticalDistributions")
                }
            }
        }
    }

    repositories {
        mavenLocal()
    }
}



android {
    namespace = "org.luffer.statisticaldistributions.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
