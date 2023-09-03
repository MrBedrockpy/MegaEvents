package me.mrbedrockpy.megaevents.hotpotato;

import me.mrbedrockpy.megaevents.Plugin;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventListener implements Listener {

    @EventHandler
    public void potatoQuit(PlayerQuitEvent event) {

        if (HotPotato.instance.potato.equals(event.getPlayer())) {

            Player potato = event.getPlayer();

            potato.removePotionEffect(PotionEffectType.GLOWING);

            potato.getInventory().clear();
            potato.getInventory().setContents(HotPotato.instance.inventories.get(potato.getUniqueId()).getContents());

            for (Player player : HotPotato.instance.players) {
                player.sendMessage(Objects.requireNonNull(Plugin.getLogs().getString("potato_quit")));
            }
            HotPotato.instance.newPotato();
        }

    }

    @EventHandler
    public void changeNewPotato(EntityDamageByEntityEvent event) {

        if (!HotPotato.instance.game) {
            return;
        }

        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {

            Player newPotato = (Player) event.getEntity();

            Player oldPotato = (Player) event.getEntity();

            List<Player> players = ArrayToListConversion(HotPotato.instance.players);

            if (players.contains(newPotato) && players.contains(oldPotato) && HotPotato.instance.potato.equals(oldPotato)) {

                HotPotato.instance.potato = newPotato;

                newPotato.sendMessage(Objects.requireNonNull(Plugin.getLogs().getString("you_is_new_potato")));

                newPotato.spawnParticle(Particle.TOTEM, newPotato.getLocation(), 1);

                newPotato.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 999999, 1, false, false));

                oldPotato.removePotionEffect(PotionEffectType.GLOWING);

            }

        }

    }

    private <T> List<T> ArrayToListConversion(T array[]) {

        List<T> list = new ArrayList<>();

        for (T t : array) {
            list.add(t);
        }

        return list;
    }

}
