package me.davamu.file;

import lombok.Getter;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.loader.HeaderMode;
import org.spongepowered.configurate.reference.ConfigurationReference;
import org.spongepowered.configurate.reference.WatchServiceListener;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Path;

public class FileReference implements Closeable {

    private WatchServiceListener listener;
    @Getter
    private ConfigurationReference<CommentedConfigurationNode> configurationReference;

    public FileReference(Path configPath) {
        this(configPath, ConfigurationOptions.defaults(), HeaderMode.PRESET, 2, NodeStyle.BLOCK);
    }

    public FileReference(Path configPath, ConfigurationOptions configurationOptions) {
        this(configPath, configurationOptions, HeaderMode.PRESET, 2, NodeStyle.BLOCK);
    }

    public FileReference(Path configPath, ConfigurationOptions configurationOptions, HeaderMode headerMode) {
        this(configPath, configurationOptions, headerMode, 2, NodeStyle.BLOCK);
    }

    public FileReference(Path configPath, ConfigurationOptions configurationOptions, HeaderMode headerMode, int indent) {
        this(configPath, configurationOptions, headerMode, indent, NodeStyle.BLOCK);
    }

    public FileReference(Path configPath, ConfigurationOptions configurationOptions, HeaderMode headerMode, int indent,
                         NodeStyle nodeStyle) {
        try {
            listener = WatchServiceListener.create();
            configurationReference = listener.listenToConfiguration(
                    file ->
                            YamlConfigurationLoader.builder()
                                    .defaultOptions(configurationOptions)
                                    .headerMode(headerMode)
                                    .path(configPath)
                                    .indent(indent)
                                    .nodeStyle(nodeStyle)
                                    .build(), configPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() throws IOException {
        listener.close();
        configurationReference.close();
    }

}
