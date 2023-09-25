package at.lav.commands;

import at.lav.SimpleHomeSystem;
import at.lav.utils.MessageUtils;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DeleteHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;

        FileConfiguration configuration = SimpleHomeSystem.getPlugin(SimpleHomeSystem.class).getConfig();

        if(configuration.contains(player.getUniqueId().toString())) {
            configuration.set(player.getUniqueId().toString(), null);
            SimpleHomeSystem.getPlugin(SimpleHomeSystem.class).saveConfig();

            // Visuelle Effekte
            player.getWorld().spawnParticle(Particle.LAVA, player.getLocation(), 100, 0.5, 0.5, 0.5, 2);
            player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 100, 0.5, 0.5, 0.5, 2);

            MessageUtils.sendPrefixedMessage(player, ChatColor.RED + "Dein Home wurde gelöscht!");
        } else {
            MessageUtils.sendPrefixedMessage(player, ChatColor.RED + "Du hast kein Home zum Löschen!");
        }
        return false;
    }

}
