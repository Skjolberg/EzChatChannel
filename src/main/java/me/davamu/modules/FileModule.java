package me.davamu.modules;

import lombok.Getter;
import lombok.extern.java.Log;
import me.davamu.file.configuration.ConfigurationSerializable;
import me.davamu.file.FileReference;
import me.davamu.file.messages.MessagesSerializable;
import net.shibacraft.library.loader.SLLoader;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.reference.ConfigurationReference;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log
public class FileModule implements SLLoader {

    @Getter
    private ConfigurationSerializable configSerializable;
    @Getter
    private MessagesSerializable messagesSerializable;
    private final static String DATA_FOLDER_PATH = "plugins/EzChatChannel";

    @Override
    public void load() {
        loadConfig();
        loadMessages();
    }

    @Override
    public void unload() {

    }

    @Override
    public void reload() {
        loadConfig();
        loadMessages();
    }

    private void loadConfig() {
        Path configPath = Paths.get(DATA_FOLDER_PATH).resolve("Config.yml");
        ConfigurationOptions configOpts = ConfigurationOptions.defaults().header("Config | by SimplePlugins");

        try (FileReference fileReference = new FileReference(configPath, configOpts)) {
            ConfigurationReference<CommentedConfigurationNode> configurationReference = fileReference.getConfigurationReference();
            ConfigurationNode node = configurationReference.loader().load();

            configSerializable = node.get(ConfigurationSerializable.class);
            node.set(ConfigurationSerializable.class, configSerializable);
            configurationReference.save(node);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMessages(){
        Path configPath = Paths.get(DATA_FOLDER_PATH).resolve("Messages.yml");
        ConfigurationOptions configOpts = ConfigurationOptions.defaults().header("Messages | by SimplePlugins");

        try (FileReference fileReference = new FileReference(configPath, configOpts)) {
            ConfigurationReference<CommentedConfigurationNode> configurationReference = fileReference.getConfigurationReference();
            ConfigurationNode node = configurationReference.loader().load();

            messagesSerializable = node.get(MessagesSerializable.class);
            node.set(MessagesSerializable.class, messagesSerializable);
            configurationReference.save(node);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
