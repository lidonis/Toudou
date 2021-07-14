import Libs.http4k_bom
import Libs.http4k_client_apache
import Libs.http4k_core
import Libs.http4k_format_jackson
import Libs.http4k_server_undertow

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(Kotlin.stdlib.jdk8)
    implementation(project(":toudou-domain"))
    implementation(platform(http4k_bom))
    implementation(http4k_core)
    implementation(http4k_server_undertow)
    implementation(http4k_client_apache)
    implementation(http4k_format_jackson)
}
