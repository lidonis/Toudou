import Libs.logback_classic

plugins {
    application
    kotlin("jvm")
}

dependencies {
    implementation(project(":toudou-domain"))
    implementation(Kotlin.stdlib.jdk8)
    implementation(Ktor.server.core)
    implementation(Ktor.server.netty)
    implementation(logback_classic)
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}
