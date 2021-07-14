import Libs.cucumber_java8
import Libs.cucumber_junit
import Libs.kotest_assertions_ktor
import Testing.Kotest

plugins {
    kotlin("jvm")
}

listOf("domain", "server").forEach {
    sourceSets {
        create("${it}Test") {}
    }
    configurations {
        "${it}TestImplementation" {
            extendsFrom(configurations.testImplementation.get())
        }
    }

    val newTest = task<Test>("${it}Test") {
        description = "Runs ${it} tests."
        group = "verification"

        testClassesDirs = sourceSets["${it}Test"].output.classesDirs
        classpath = sourceSets["${it}Test"].runtimeClasspath
        shouldRunAfter("test")
    }
    tasks.check {
        dependsOn(newTest)
    }
}

dependencies {
    testImplementation(Kotlin.stdlib.jdk8)
    testImplementation(cucumber_java8)
    testImplementation(cucumber_junit)
    testImplementation(Kotest.assertions.core)
    "domainTestImplementation"(project(":toudou-domain"))
    "serverTestImplementation"(project(":toudou-server"))
    "serverTestImplementation"(Ktor.server.testHost)
    "serverTestImplementation"(kotest_assertions_ktor)
    "serverTestImplementation"(Kotest.assertions.json)
}

tasks.test {
    useJUnitPlatform()
}
