package me.mrbedrockpy.megaevents.commands;

import me.mrbedrockpy.megaevents.Plugin;
import me.mrbedrockpy.megaevents.hotpotato.HotPotato;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class StartEvent implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {

            if (args[0].equals("potato")) {

                new HotPotato().start();

            } else if (args[0].equals("hotpotato")) {

                new HotPotato().start();

            } else {

                sender.sendMessage(Objects.requireNonNull(Plugin.getLogs().getString("not_found_event")));

                return false;
            }

            sender.sendMessage(Objects.requireNonNull(Plugin.getLogs().getString("event_started")));

            return true;
        }

        sender.sendMessage(Objects.requireNonNull(Plugin.getLogs().getString("not_rules")));

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            return Arrays.asList(
                    "potato",
                    "hotpotato"
            );
        }

        return null;
    }
}
