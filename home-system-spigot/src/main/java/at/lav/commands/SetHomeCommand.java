package at.lav.commands;

import at.lav.SimpleHomeSystem;
import at.lav.api.IHomeService;
import at.lav.services.HomeServiceSpigot;
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

public class SetHomeCommand implements CommandExecutor {
    private final IHomeService homeService;

    public SetHomeCommand(IHomeService homeService) {
        this.homeService = homeService;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;

        if(args.length != 1) {
            MessageUtils.sendPrefixedMessage(player, ChatColor.GREEN + "Korrektes Benutzen: /sethome [name]");
            return true;
        }

        Location playerLocation = player.getLocation();

        //FileConfiguration configuration = SimpleHomeSystem.getPlugin(SimpleHomeSystem.class).getConfig();
        //configuration.set(player.getUniqueId().toString(), playerLocation);
        //SimpleHomeSystem.getPlugin(SimpleHomeSystem.class).saveConfig();

        MessageUtils.sendPrefixedMessage(player, ChatColor.GREEN + "LADE IN DATENBANK!!!!!!!");

        homeService.setHome(player.getUniqueId(), args[0], player.getLocation());

        MessageUtils.sendPrefixedMessage(player, ChatColor.GREEN + "BEENDET DAS LADEN DATENBANK!!!!!!!");

        // Visuelle Effekte
        player.getWorld().spawnParticle(Particle.END_ROD, playerLocation, 300, 0.5, 0.5, 0.5, 2);
        player.getWorld().spawnParticle(Particle.HEART, playerLocation.add(0, 2, 0), 50);

        MessageUtils.sendPrefixedMessage(player, ChatColor.GREEN + "Dein Home wurde gesetzt!");
        return false;
    }
}
