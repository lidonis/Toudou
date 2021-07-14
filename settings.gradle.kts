rootProject.name = "toudou"
include("toudou-acceptance", "toudou-domain", "toudou-server", "toudou-server-ktor")
plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.10.1"
}

refreshVersions {
    enableBuildSrcLibs()
    extraArtifactVersionKeyRules
}
