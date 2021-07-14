import Libs.cucumber_java8
import Libs.cucumber_junit
import Libs.http4k_bom
import Libs.http4k_core
import Libs.http4k_format_jackson
import Libs.http4k_testing_kotest
import Testing.kotest

plugins {
    kotlin("jvm")
}

sourceSets {
    create("domainTest") {}
    create("serverTest") {}
}

val domainTestImplementation: Configuration by configurations.getting {
    extendsFrom(configurations.testImplementation.get())
}

val serverTestImplementation: Configuration by configurations.getting {
    extendsFrom(configurations.testImplementation.get())
}

dependencies {
    testImplementation(Kotlin.stdlib.jdk8)
    testImplementation(cucumber_java8)
    testImplementation(cucumber_junit)
    testImplementation(kotest.assertions.core)
    "domainTestImplementation"(project(":toudou-domain"))
    "serverTestImplementation"(project(":toudou-server"))
    "serverTestImplementation"(platform(http4k_bom))
    "serverTestImplementation"(http4k_core)
    "serverTestImplementation"(http4k_testing_kotest)
    "serverTestImplementation"(http4k_format_jackson)
    "serverTestImplementation"(kotest.assertions.json)
}

tasks.test {
    useJUnitPlatform()
}

val domainTest = task<Test>("domainTest") {
    description = "Runs domain tests."
    group = "verification"

    testClassesDirs = sourceSets["domainTest"].output.classesDirs
    classpath = sourceSets["domainTest"].runtimeClasspath
    shouldRunAfter("test")
}

val serverTest = task<Test>("serverTest") {
    description = "Runs server tests."
    group = "verification"

    testClassesDirs = sourceSets["serverTest"].output.classesDirs
    classpath = sourceSets["serverTest"].runtimeClasspath
    shouldRunAfter("test")
}

tasks.check {
    dependsOn(domainTest)
    dependsOn(serverTest)
}

