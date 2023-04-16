package me.davamu.console.logger;

import com.google.common.base.Strings;
import net.shibacraft.library.chat.SLTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ConsoleHandler extends Handler {

    private final String DEBUG_PATTERN = "%s%s %s";
    private final String NORMAL_PATTERN = "%s %s";
    private final String PREFIX = "[EzChatChannel] |";
    private final String DEBUG_PREFIX = " &bDEBUG:";

    private final int FINE = 500;
    private final int INFO = 800;
    private final int WARNING = 900;
    private final int SEVERE = 1000;

    private final ConsoleCommandSender console;

    protected ConsoleHandler(JavaPlugin plugin) {
        this.console = plugin.getServer().getConsoleSender();
    }

    @Override
    public void publish(LogRecord record) {

        String levelName;
        final int recordLevel = record.getLevel().intValue();
        String message;

        if (recordLevel < INFO) {
            levelName = DEBUG_PREFIX;
            message = String.format(DEBUG_PATTERN, PREFIX, levelName, record.getMessage());
            sendRawNormal(record, message);
        } else if (recordLevel == INFO){
            message = String.format(NORMAL_PATTERN, PREFIX, record.getMessage());
            sendRawNormal(record, message);
        } else {
            message = String.format(NORMAL_PATTERN, PREFIX, record.getMessage());
            sendRawCritic(record, message);
        }

    }

    private void sendRawNormal(LogRecord record, String msg) {
        if (Strings.isNullOrEmpty(msg)) return;

        String msgColor = SLTextColor.color(msg);

        if (console == null) {
            Bukkit.getLogger().log(record.getLevel(), msg);
        } else {
            console.sendMessage(msgColor);
        }
    }

    private void sendRawCritic(LogRecord record, String msg) {
        if (Strings.isNullOrEmpty(msg)) return;

        Bukkit.getLogger().log(record.getLevel(), msg);
    }

    @Override
    public void flush() {}

    @Override
    public void close() throws SecurityException {}

}
