package me.davamu.console.logger;

import lombok.extern.java.Log;
import me.davamu.modules.FileModule;
import net.shibacraft.library.loader.SLLoader;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

@Log
public class ConsoleLoader implements SLLoader {

    private final Logger parentLogger;
    private final ConsoleHandler consoleHandler;
    private final FileModule fileModule;

    public ConsoleLoader(JavaPlugin plugin, FileModule fileModule) {
        this.parentLogger = Logger.getLogger("me.davamu");
        this.consoleHandler = new ConsoleHandler(plugin);
        this.fileModule = fileModule;
    }

    @Override
    public void load() {
        parentLogger.setUseParentHandlers(false);
        parentLogger.addHandler(consoleHandler);
        debug(fileModule.getConfigSerializable().isDebug());
    }

    @Override
    public void unload() {

    }

    @Override
    public void reload() {
        debug(fileModule.getConfigSerializable().isDebug());
    }

    public void debug(boolean debug){
        if (debug) {
            parentLogger.setLevel(Level.FINE);
        } else {
            parentLogger.setLevel(Level.INFO);
        }
    }

}
