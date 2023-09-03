package me.mrbedrockpy.megaevents;

import me.mrbedrockpy.megaevents.commands.StartEvent;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {

    private static Plugin instance;

    private static ConfigurationSection log_messages;

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        log_messages = getConfig().getConfigurationSection("log_messages");

        getCommand("startevent").setExecutor(new StartEvent());

    }

    public static Plugin getInstance() {
        return instance;
    }

    public static ConfigurationSection getLogs() {
        return log_messages;
    }
}
