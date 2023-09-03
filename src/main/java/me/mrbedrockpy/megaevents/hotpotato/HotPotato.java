package me.mrbedrockpy.megaevents.hotpotato;

import me.mrbedrockpy.megaevents.Event;
import me.mrbedrockpy.megaevents.Plugin;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class HotPotato implements Event {

    public static HotPotato instance = new HotPotato();

    public boolean game;

    public Player[] players;

    public Player potato;

    public Map<UUID, Inventory> inventories = new HashMap<>();

    @Override
    public void init() {
        Plugin.getInstance().getServer().getPluginManager().registerEvents(new EventListener(), Plugin.getInstance());
    }

    @Override
    public void start() {

        init();

        players = Plugin.getInstance().getServer().getOnlinePlayers().toArray(new Player[0]);

        new BukkitRunnable() {
            @Override
            public void run() {

                game = true;

                long startTime = new Date().getTime();

                for (Player player : players) {
                    inventories.put(player.getUniqueId(), player.getInventory());
                    player.getInventory().clear();
                }

                while (game) {

                    if (startTime + Plugin.getInstance().getConfig().getInt("duration_hot_potato") <= new Date().getTime()) {
                        game = false;
                    }

                }

                for (Player player : players) {
                    player.sendMessage(Objects.requireNonNull(Plugin.getLogs().getString("event_ended")));
                    player.getInventory().clear();
                    player.getInventory().setContents(inventories.get(player.getUniqueId()).getContents());
                }

                players = null;

                potato = null;

                inventories = null;
            }
         }.runTask(Plugin.getInstance());

    }

    public void newPotato() {
        potato = players[new Random().nextInt(players.length)];
    }

}
