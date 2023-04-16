package me.davamu.commands;

import me.davamu.modules.FileModule;
import me.davamu.modules.MainModule;
import me.davamu.util.PlayerUtil;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command(names = {"EzChatChannel"}, permission = "ezchatchannel.use", desc = "EzChatChannel command")
public class MainCommand implements CommandClass {

    private final MainModule mainModule;
    private final FileModule fileModule;

    public MainCommand(MainModule mainModule){
        this.mainModule = mainModule;
        this.fileModule = mainModule.getFileModule();
    }

    @Command(names = "")
    public void onMainCommand(@Sender CommandSender sender) {
        sender.sendMessage("It needs arguments.");
    }

    @Command(names = "reload", permission = "ezchatchannel.admin")
    public void onReloadCommand(@Sender CommandSender sender) {
        mainModule.reload();
        sender.sendMessage(fileModule.getMessagesSerializable().getReload());
    }

    @Command(names = "local")
    public void onLocalCommand(@Sender Player sender) {
        PlayerUtil.localChannel.add(sender);
        sender.sendMessage(fileModule.getMessagesSerializable().getLocal());
    }

    @Command(names = "global")
    public void onGlobalCommand(@Sender Player sender) {
        PlayerUtil.localChannel.remove(sender);
        sender.sendMessage(fileModule.getMessagesSerializable().getGlobal());
    }


}
