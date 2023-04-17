package me.davamu.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.davamu.modules.FileModule;
import me.davamu.util.PlayerUtil;
import net.shibacraft.library.chat.SLTextColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ChannelPlaceholderAPI extends PlaceholderExpansion {

    private final FileModule fileModule;

    public ChannelPlaceholderAPI(FileModule fileModule) {
        this.fileModule = fileModule;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "ezchatchannel";
    }

    @Override
    public @NotNull String getAuthor() {
        return "DaVaMu";
    }

    @Override
    public @NotNull String getVersion() {
        return "0.0.1";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, String str) {
        if (PlayerUtil.localChannel.contains(player)) return SLTextColor.color(fileModule.getMessagesSerializable().getLocalChannel());
        return SLTextColor.color(fileModule.getMessagesSerializable().getGlobalChannel());
    }

}
