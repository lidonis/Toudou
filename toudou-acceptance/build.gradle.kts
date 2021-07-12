plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation(kotlin("stdlib"))
    testImplementation(project(":toudou-domain"))
    testImplementation("io.cucumber:cucumber-java8:6.10.4")
    testImplementation("io.cucumber:cucumber-junit:6.10.4")
    testImplementation("io.kotest:kotest-assertions-core:4.6.0")
}
