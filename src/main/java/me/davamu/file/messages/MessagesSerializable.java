package me.davamu.file.messages;

import lombok.Getter;
import lombok.Setter;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@Setter
@Getter
@ConfigSerializable
public class MessagesSerializable {

    private String reload = "Plugin reloaded!";
    private String local = "You are now on the local channel!";
    private String localChannel = "Local";
    private String global = "You are now on the global channel!";
    private String globalChannel = "Global";

}
