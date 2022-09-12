rootProject.name = "toudou"
include("toudou-acceptance", "toudou-domain", "toudou-server")
plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.50.0"
}

refreshVersions {
    enableBuildSrcLibs()
    extraArtifactVersionKeyRules
}
