package at.lav.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtils {

    public static void sendPrefixedMessage(Player player, String message) {
        player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "Home-System" + ChatColor.GRAY + "]: " + ChatColor.DARK_GRAY + message);
    }
}