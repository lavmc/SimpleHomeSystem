package at.lav.commands;

import at.lav.api.IHomeService;
import at.lav.utils.MessageUtils;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DeleteHomeCommand implements CommandExecutor {

    private final IHomeService homeService;

    public DeleteHomeCommand(IHomeService homeService) {
        this.homeService = homeService;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;

        if(args.length != 1) {
            MessageUtils.sendPrefixedMessage(player, ChatColor.RED + "Korrektes Benutzen: /delhome [name]");
            return true;
        }

        if(homeService.deleteHome(player.getUniqueId(), args[0])) {

            // Visuelle Effekte
            player.getWorld().spawnParticle(Particle.LAVA, player.getLocation(), 100, 0.5, 0.5, 0.5, 2);
            player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 100, 0.5, 0.5, 0.5, 2);

            MessageUtils.sendPrefixedMessage(player, ChatColor.RED + "Dein Home wurde erfolgreich gelöscht!");
        } else {
            MessageUtils.sendPrefixedMessage(player, ChatColor.RED + "Du hast kein Home zum Löschen!");
        }
        return false;
    }

}
