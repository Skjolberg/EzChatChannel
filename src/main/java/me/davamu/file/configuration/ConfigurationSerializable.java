package me.davamu.file.configuration;

import lombok.Getter;
import lombok.Setter;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@Setter
@Getter
@ConfigSerializable
public class ConfigurationSerializable {

    private boolean debug = false;
    private int local = 300;

}
