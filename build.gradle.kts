plugins {
    java
    alias(libs.plugins.shadow)
}

group = "${project.property("group")}"
version = "${project.property("version")}"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.unnamed.team/repository/unnamed-public/")
    maven("https://jitpack.io/")
    maven("https://repo.alessiodp.com/releases/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    implementation(libs.libby)
    compileOnly(libs.commandFlow)
    compileOnly(libs.paper)
    compileOnly(libs.adventureNew)
    compileOnly(libs.configurateCore)
    compileOnly(libs.configurateYaml)
    compileOnly(libs.shibacraft)
    compileOnly(libs.papi)
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
}

tasks {
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
    assemble {
        dependsOn(shadowJar)
    }
    build {
        dependsOn(shadowJar)
    }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    shadowJar {
        val libs = "me.davamu.libs"
        relocate("me.fixeddev", "$libs.cmdFlow")
        relocate("org.spongepowered.configurate", "$libs.configurate")
        relocate("net.shibacraft", "$libs.shibacraftLibrary")
        archiveFileName.set("${project.name}-${project.version}.jar")
        minimize()
    }

    processResources {
        filesMatching("plugin.yml") {
            expand(
                "name" to rootProject.name,
                "version" to project.version,
                "group" to project.group,
                "author" to project.property("author"),
                "description" to project.property("description"),
                "minecraftVersion" to project.property("minecraftVersion"),
            )
        }
    }

}