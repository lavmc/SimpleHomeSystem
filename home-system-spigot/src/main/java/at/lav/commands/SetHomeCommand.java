package at.lav.commands;

import at.lav.SimpleHomeSystem;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) return true;

        Location playerLocation = ((Player) commandSender).getLocation();

        FileConfiguration configuration = SimpleHomeSystem.getPlugin(SimpleHomeSystem.class).getConfig();

        configuration.set(((Player) commandSender).getUniqueId().toString(), playerLocation);
        SimpleHomeSystem.getPlugin(SimpleHomeSystem.class).saveConfig();

        return false;
    }
}
