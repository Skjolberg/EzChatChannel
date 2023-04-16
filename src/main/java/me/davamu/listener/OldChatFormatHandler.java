package me.davamu.listener;

import me.davamu.modules.FileModule;
import me.davamu.util.PlayerUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashSet;
import java.util.Set;

public class OldChatFormatHandler implements ChatFormatHandler<AsyncPlayerChatEvent> {

    private final FileModule fileModule;

    public OldChatFormatHandler(FileModule fileModule) {
        this.fileModule = fileModule;
    }

    @Override
    public void accept(AsyncPlayerChatEvent event) {

        // global channel
        if (!PlayerUtil.localChannel.contains(event.getPlayer())) return;

        // distance
        final int distance = fileModule.getConfigSerializable().getLocal();

        // location from message
        Location mainLoc = event.getPlayer().getLocation();

        // new viewers
        Set<Player> players = new HashSet<>();

        // for each of them
        event.getRecipients().forEach(player -> {
            Location receiverLoc = player.getLocation();
            if (mainLoc.distance(receiverLoc) <= distance) {
                players.add(player);
            }
        });

        // delete viewers
        event.getRecipients().clear();

        // we add the viewers we want to send the message to
        event.getRecipients().addAll(players);

    }

}
