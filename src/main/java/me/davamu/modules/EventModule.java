package me.davamu.modules;

import io.papermc.paper.event.player.AsyncChatEvent;
import lombok.extern.java.Log;
import me.davamu.EzChatChannel;
import me.davamu.listener.ChatFormatHandler;
import me.davamu.listener.NewChatFormatHandler;
import me.davamu.listener.OldChatFormatHandler;
import me.davamu.placeholder.ChannelPlaceholderAPI;
import net.shibacraft.library.loader.SLLoader;
import org.bukkit.Bukkit;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerEvent;

@Log
public class EventModule implements SLLoader {

    private final EzChatChannel plugin;
    private final FileModule fileModule;

    public EventModule(EzChatChannel plugin, FileModule fileModule) {
        this.plugin = plugin;
        this.fileModule = fileModule;
    }

    @Override
    public void load() {
        registerChat();

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            ChannelPlaceholderAPI channelPlaceHolderAPI = new ChannelPlaceholderAPI(fileModule);
            channelPlaceHolderAPI.register();
            log.info("PlaceholderAPI has been registered.");
        } else {
            log.warning("PlaceholderAPI was not found, you will not be able to display the placeholder indicating " +
                    "which channel you are using!");
        }

    }

    @Override
    public void unload() {

    }

    @Override
    public void reload() {

    }

    public void registerChat() {
        ChatFormatHandler chatFormatHandler;
        Class<? extends PlayerEvent> eventClazz;

        if (isPaperChat()) {
            chatFormatHandler = new NewChatFormatHandler(fileModule);
            eventClazz = AsyncChatEvent.class;
            log.info("Registering paper chat");
        } else {
            chatFormatHandler = new OldChatFormatHandler(fileModule);
            eventClazz = AsyncPlayerChatEvent.class;
            log.info("Registering legacy chat");
        }

        plugin.getServer().getPluginManager().registerEvent(eventClazz, new Listener() {
        }, EventPriority.LOWEST, (listener, event) -> {
            chatFormatHandler.accept((PlayerEvent) event);
        }, plugin, true);
    }

    public boolean isPaperChat() {
        try {
            Class.forName("io.papermc.paper.event.player.AbstractChatEvent");
            //Class.forName("io.papermc.paper.event.player.AsyncChatEvent");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
