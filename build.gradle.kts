import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.0.20-Beta1"
    id("org.jetbrains.intellij.platform") version "2.0.0-beta7"
    // id("org.jetbrains.intellij") version "1.17.4"
}

group = "me.andreikud"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

intellijPlatform {
    pluginConfiguration {
        name = "Kotlin Arguments Filler"
    }
}

dependencies {
    // implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    intellijPlatform {
        // intellijIdeaCommunity("2024.1.4")
        intellijIdeaCommunity("2023.2")
        // androidStudio("2023.3.1.19")
        bundledPlugins("com.intellij.java", "org.jetbrains.kotlin")
        // pluginVerifier()
        // zipSigner()
        instrumentationTools()
        // testFramework(TestFrameworkType.Platform.JUnit4)
    }
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
        // noJdk = true
        allWarningsAsErrors = true
    }
}

// java {
//     sourceCompatibility = JavaVersion.VERSION_17
//     targetCompatibility = JavaVersion.VERSION_17
// }

tasks {
    // withType<JavaCompile> {
    //     sourceCompatibility = "17"
    //     targetCompatibility = "17"
    // }
    // withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    //     kotlinOptions.jvmTarget = "17"
    // }

    patchPluginXml {
        pluginVersion.set("${project.version}")
        sinceBuild.set("232")
        untilBuild.set("242.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
