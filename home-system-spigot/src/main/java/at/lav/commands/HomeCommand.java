package at.lav.commands;

import at.lav.api.IHomeService;
import at.lav.utils.MessageUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HomeCommand implements CommandExecutor {

    private final IHomeService homeService;


    public HomeCommand(IHomeService homeService) {
        this.homeService = homeService;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;

        if(args.length != 1) {
            MessageUtils.sendPrefixedMessage(player, ChatColor.RED + "Korrektes Benutzen: /home [name]");
            return true;
        }

        Location location = homeService.getHome(player.getUniqueId(), args[0]);

        if(location != null) {
            player.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), 3);
            player.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, player.getLocation(), 200, 0.5, 0.5, 0.5, 2);

            player.teleport(location);

            player.getWorld().spawnParticle(Particle.DRAGON_BREATH, location, 100);
            player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, location, 200, 0.5, 0.5, 0.5, 2);

            MessageUtils.sendPrefixedMessage(player, ChatColor.GREEN + "Willkommen bei "+args[0]+"!");
        } else {
            MessageUtils.sendPrefixedMessage(player, ChatColor.RED + "Dieses Zuhause kann nicht gefunden werden!");
        }
        return false;
    }

}
