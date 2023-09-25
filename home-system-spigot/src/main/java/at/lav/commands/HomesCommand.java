package at.lav.commands;

import at.lav.utils.HomesGUIManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class HomesCommand implements CommandExecutor {

    private final HomesGUIManager guiManager;

    public HomesCommand(HomesGUIManager guiManager) {
        this.guiManager = guiManager;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;

        guiManager.openHomesGUI(player, 1);

        return true;
    }
}
