package me.davamu.commands.Internal;

import me.davamu.EzChatChannel;
import me.davamu.modules.MainModule;
import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.SimplePartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import me.fixeddev.commandflow.translator.DefaultTranslator;
import me.davamu.commands.MainCommand;
import net.shibacraft.library.loader.SLLoader;

public class CommandLoader implements SLLoader {

    private final CommandManager commandManager;
    private final MainModule mainModule;
    private final CommandTranslatorProvider commandTranslatorProvider;

    public CommandLoader(CommandTranslatorProvider commandTranslatorProvider, MainModule mainModule) {
        this.commandManager = new BukkitCommandManager(EzChatChannel.getPlugin().getName());
        this.commandManager.setTranslator(new DefaultTranslator(commandTranslatorProvider));
        this.mainModule = mainModule;
        this.commandTranslatorProvider = commandTranslatorProvider;
    }

    @Override
    public void load() {
        PartInjector partInjector = new SimplePartInjector();
        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());

        AnnotatedCommandTreeBuilder treeBuilder = new AnnotatedCommandTreeBuilderImpl(partInjector);

        commandManager.registerCommands(treeBuilder.fromClass(new MainCommand(mainModule)));

    }

    @Override
    public void unload() {
        commandManager.unregisterAll();
    }

    @Override
    public void reload() {
        commandTranslatorProvider.reload();
    }

}
