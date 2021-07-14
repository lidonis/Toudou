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
    testImplementation(kotlin("stdlib"))
    testImplementation("io.cucumber:cucumber-java8:6.10.4")
    testImplementation("io.cucumber:cucumber-junit:6.10.4")
    testImplementation("io.kotest:kotest-assertions-core:4.6.1")
    "domainTestImplementation"(project(":toudou-domain"))
    "serverTestImplementation"(project(":toudou-server"))
    "serverTestImplementation"(platform("org.http4k:http4k-bom:4.9.10.0"))
    "serverTestImplementation"("org.http4k:http4k-core")
    "serverTestImplementation"("org.http4k:http4k-testing-kotest")
    "serverTestImplementation"("org.http4k:http4k-format-jackson")
    "serverTestImplementation"("io.kotest:kotest-assertions-json:4.6.0")
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

