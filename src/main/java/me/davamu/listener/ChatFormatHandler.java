package me.davamu.listener;

import org.bukkit.event.player.PlayerEvent;
import org.bukkit.util.Consumer;

public interface ChatFormatHandler<E extends PlayerEvent> extends Consumer<E> {
    @Override
    void accept(E event);
}
