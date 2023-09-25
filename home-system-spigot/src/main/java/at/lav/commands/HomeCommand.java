package at.lav.commands;

import at.lav.SimpleHomeSystem;
import at.lav.utils.MessageUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;

        FileConfiguration configuration = SimpleHomeSystem.getPlugin(SimpleHomeSystem.class).getConfig();

        if(configuration.contains(player.getUniqueId().toString())) {
            Location homeLocation = (Location) configuration.get(player.getUniqueId().toString());

            // Vor-Teleport Effekte
            player.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), 3);
            player.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, player.getLocation(), 200, 0.5, 0.5, 0.5, 2);

            player.teleport(homeLocation);

            // Nach-Teleport Effekte
            player.getWorld().spawnParticle(Particle.DRAGON_BREATH, homeLocation, 100);
            player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, homeLocation, 200, 0.5, 0.5, 0.5, 2);

            MessageUtils.sendPrefixedMessage(player, ChatColor.GREEN + "Willkommen zuhause!");
        } else {
            MessageUtils.sendPrefixedMessage(player, ChatColor.RED + "Du musst zuerst ein Home setzen!");
        }
        return false;
    }

}
