rootProject.name = "toudou"
include("toudou-acceptance", "toudou-domain", "toudou-server")
plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.40.2"
}

refreshVersions {
    enableBuildSrcLibs()
    extraArtifactVersionKeyRules
}
