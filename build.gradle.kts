plugins {
    application
    kotlin("jvm") version "1.4.21"
    id("com.justai.jaicf.jaicp-build-plugin") version "0.1.1"
}

group = "com.justai.jaicf"
version = "1.0.0"

val jaicf = "1.1.3"
val logback = "1.2.3"

// Main class to run application on heroku. Either JaicpPollerKt, or JaicpServerKt. Will propagate to .jar main class.
application {
    mainClassName = "com.justai.jaicf.template.connections.JaicpServerKt"
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven("https://jitpack.io")
//    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.just-ai.jaicf:core:$jaicf")
    implementation("com.just-ai.jaicf:jaicp:$jaicf")
    implementation("com.just-ai.jaicf:aimybox:$jaicf")
    implementation("com.just-ai.jaicf:telegram:$jaicf")
    implementation("com.just-ai.jaicf:alexa:$jaicf")
    implementation("com.just-ai.jaicf:google-actions:$jaicf")
    implementation("com.just-ai.jaicf:facebook:$jaicf")
    implementation("com.just-ai.jaicf:viber:$jaicf")
    implementation("com.just-ai.jaicf:slack:$jaicf")
    implementation("com.just-ai.jaicf:yandex-alice:$jaicf")
    implementation("com.just-ai.jaicf:caila:$jaicf")

    implementation("ch.qos.logback:logback-classic:$logback")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

tasks.create("stage") {
    dependsOn("shadowJar")
}

tasks.withType<com.justai.jaicf.plugins.jaicp.build.JaicpBuild> {
    mainClassName.set(application.mainClassName)
}
