package at.lav.commands;

import at.lav.SimpleHomeSystem;
import org.bukkit.Location;
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

        FileConfiguration configuration = SimpleHomeSystem.getPlugin(SimpleHomeSystem.class).getConfig();

        if(configuration.contains(((Player) commandSender).getUniqueId().toString())) {
            ((Player) commandSender).teleport((Location) configuration.get(((Player) commandSender).getUniqueId().toString()));
        } else {
            // Feedback to User (Du musst als erstes ein home setzen)
        }

        return false;
    }
}
