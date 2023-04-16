package me.davamu;

import lombok.Getter;
import me.davamu.lib.ManagerLib;
import me.davamu.modules.MainModule;
import org.bukkit.plugin.java.JavaPlugin;

public class EzChatChannel extends JavaPlugin {

    @Getter
    private static EzChatChannel plugin;
    private MainModule mainModule;

    @Override
    public void onLoad() {
        new ManagerLib(this);
    }

    @Override
    public void onEnable() {
        EzChatChannel.plugin = this;

        mainModule = new MainModule(this);
        mainModule.load();

    }

    @Override
    public void onDisable() {
        mainModule.unload();
    }


}
