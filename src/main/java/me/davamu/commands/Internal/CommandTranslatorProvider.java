package me.davamu.commands.Internal;

import lombok.Getter;
import me.fixeddev.commandflow.Namespace;
import me.fixeddev.commandflow.translator.DefaultMapTranslationProvider;
import net.shibacraft.library.loader.SLLoader;

import java.util.HashMap;
import java.util.Map;

@Getter
public class CommandTranslatorProvider extends DefaultMapTranslationProvider implements SLLoader {

    public CommandTranslatorProvider() {
        overwriteMessages();
    }

    @Override
    public void load() {

    }

    @Override
    public void unload() {

    }

    @Override
    public void reload() {
        overwriteMessages();
    }

    public String getTranslation(String key) {
        return translations.get(key);
    }

    @Override
    public String getTranslation(Namespace namespace, String key) {
        return getTranslation(key);
    }

    private void overwriteMessages() {
        translations.put("command.subcommand.invalid", "This subcommand does not exist");
        translations.put("command.no-permission", "You do not have permission to execute this command");
        translations.put("sender.only-player", "Only players can execute this command!");
        translations.put("sender.unknown", "The sender for the command is unknown!");
    }

}
