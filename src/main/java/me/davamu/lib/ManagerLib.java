package me.davamu.lib;

import lombok.extern.java.Log;
import me.davamu.EzChatChannel;
import net.byteflux.libby.BukkitLibraryManager;
import net.byteflux.libby.Library;
import net.byteflux.libby.relocation.Relocation;

@Log
public class ManagerLib {

    public ManagerLib(EzChatChannel plugin) {
        final String PATH = "me.davamu.libs.";
        final BukkitLibraryManager bukkitLibraryManager = new BukkitLibraryManager(plugin, "libs");
        bukkitLibraryManager.addMavenCentral();
        bukkitLibraryManager.addJitPack();
        bukkitLibraryManager.addRepository("https://repo.unnamed.team/repository/unnamed-public/");

        final Relocation configurate = new Relocation("org{}spongepowered{}configurate", PATH + "configurate");

        final Library cfBukkit = Library.builder()
                .groupId("me{}fixeddev")
                .artifactId("commandflow-bukkit")
                .version("0.5.2")
                .id("cmFlowBukkit")
                .relocate("me{}fixeddev", PATH + "cmdFlow")
                .build();

        final Library cfUniversal = Library.builder()
                .groupId("me{}fixeddev")
                .artifactId("commandflow-universal")
                .version("0.5.3")
                .id("cmFlowUniversal")
                .relocate("me{}fixeddev", PATH + "cmdFlow")
                .build();

        final Library kyoriApi = Library.builder()
                .groupId("net{}kyori")
                .artifactId("text-api")
                .version("3.0.0-stripped")
                .id("kyoriApi")
                .relocate("net{}kyori{}text-api", PATH + "kyori")
                .build();

        final Library kyoriLegacy = Library.builder()
                .groupId("net{}kyori")
                .artifactId("text-serializer-legacy")
                .version("3.0.0-stripped")
                .id("kyoriLegacy")
                .relocate("net{}kyori{}text-serializer-legacy", PATH + "kyori")
                .build();

        final Library kyoriGson = Library.builder()
                .groupId("net{}kyori")
                .artifactId("text-serializer-gson")
                .version("3.0.0-stripped")
                .id("kyoriGson")
                .relocate("net{}kyori{}text-serializer-gson", PATH + "kyori")
                .build();

        final Library shibacraftLibrary = Library.builder()
                .groupId("com{}github{}Skjolberg")
                .artifactId("ShibacraftLibrary")
                .version("0.1.0")
                .id("shibacraftLibrary")
                .relocate("net{}shibacraft", PATH + "shibacraftLibrary")
                .build();

        final Library configurateCore = Library.builder()
                .groupId("org{}spongepowered")
                .artifactId("configurate-core")
                .version("4.1.2")
                .id("configurateCore")
                .relocate(configurate)
                .build();

        final Library configurateYaml = Library.builder()
                .groupId("org{}spongepowered")
                .artifactId("configurate-yaml")
                .version("4.1.2")
                .id("configurateYaml")
                .relocate(configurate)
                .build();

        final Library geantyref = Library.builder()
                .groupId("io{}leangen{}geantyref")
                .artifactId("geantyref")
                .version("1.3.13")
                .id("geantyref")
                .relocate(configurate)
                .build();

        bukkitLibraryManager.loadLibrary(cfBukkit);
        bukkitLibraryManager.loadLibrary(cfUniversal);
        bukkitLibraryManager.loadLibrary(kyoriApi);
        bukkitLibraryManager.loadLibrary(kyoriLegacy);
        bukkitLibraryManager.loadLibrary(kyoriGson);
        bukkitLibraryManager.loadLibrary(shibacraftLibrary);
        bukkitLibraryManager.loadLibrary(configurateCore);
        bukkitLibraryManager.loadLibrary(configurateYaml);
        bukkitLibraryManager.loadLibrary(geantyref);
    }

}
