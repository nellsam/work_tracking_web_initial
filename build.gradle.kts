plugins {
    kotlin("js") version "1.4.20"
    kotlin("plugin.serialization") version "1.4.20"
}

group = "me.samue"
version = "1.0"

repositories {
    jcenter()
    google()
    mavenCentral()

    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-js-wrappers") }
    maven { url = uri("https://kotlin.bintray.com/kotlinx") }
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
}

dependencies {

    implementation(kotlin("stdlib-js"))
    testImplementation(kotlin("test-js"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")

    implementation("org.jetbrains:kotlin-react:16.13.1-pre.105-kotlin-1.3.72")
    implementation("org.jetbrains:kotlin-react-dom:16.13.1-pre.105-kotlin-1.3.72")
    implementation(npm("react", "16.13.1"))
    implementation(npm("react-dom", "16.13.1"))

    // Kotlin Styled
    implementation("org.jetbrains:kotlin-styled:1.0.0-pre.110-kotlin-1.4.0")
    implementation(npm("styled-components", "~5.1.1"))
    implementation(npm("inline-style-prefixer", "~6.0.0"))

    implementation(npm("@material-ui/core", "4.11.3"))
    implementation(npm("react-image-gallery", "1.0.8"))
    implementation(npm("react-google-recaptcha", "2.1.0"))

    implementation(npm("react-hot-loader", "^4.12.20"))

    // Firebase
    implementation("dev.gitlive:firebase-app:1.1.0")
    implementation("dev.gitlive:firebase-common:1.1.0")
    implementation("dev.gitlive:firebase-database:1.1.0")
    implementation("dev.gitlive:firebase-auth:1.1.0")
}

kotlin {
    js(LEGACY) {
        useCommonJs()
        browser {
            binaries.executable()
            webpackTask {
                cssSupport.enabled = true
            }
            runTask {
                cssSupport.enabled = true
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
        binaries.executable()
    }
}

tasks.register("stage") {
    dependsOn("build")
}