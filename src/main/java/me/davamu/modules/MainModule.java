package me.davamu.modules;

import lombok.Getter;
import me.davamu.EzChatChannel;
import me.davamu.commands.Internal.CommandLoader;
import me.davamu.commands.Internal.CommandTranslatorProvider;
import me.davamu.console.logger.ConsoleLoader;
import net.shibacraft.library.loader.SLLoader;

@Getter
public class MainModule implements SLLoader {

    private final EzChatChannel plugin;
    private CommandLoader commandLoader;
    private FileModule fileModule;
    private ConsoleLoader consoleLoader;

    public MainModule(EzChatChannel plugin){
        this.plugin = plugin;
    }

    @Override
    public void load() {
        fileModule = new FileModule();
        fileModule.load();

        consoleLoader = new ConsoleLoader(plugin, fileModule);
        consoleLoader.load();

        final CommandTranslatorProvider commandTranslatorProvider = new CommandTranslatorProvider();
        commandLoader = new CommandLoader(commandTranslatorProvider, this);
        commandLoader.load();

        EventModule eventModule = new EventModule(plugin, fileModule);
        eventModule.load();

    }

    @Override
    public void unload() {

    }

    @Override
    public void reload() {
        fileModule.reload();
        consoleLoader.reload();
        commandLoader.reload();
    }

}
