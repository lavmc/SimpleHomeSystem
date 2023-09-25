package at.lav.utils;

import at.lav.api.IHomeService;
import at.lav.model.Home;
import at.lav.services.HomeServiceSpigot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class HomesGUIManager {

    private final IHomeService homeService;

    public HomesGUIManager(IHomeService homeService) {
        this.homeService = homeService;
    }

    public void openHomesGUI(Player player, int page) {
        String title = ChatColor.GREEN + ChatColor.BOLD.toString() + "Ihre Homes " + ChatColor.GOLD + ChatColor.BOLD + "(Seite " + page + " von 3)";
        Inventory gui = Bukkit.createInventory(player, 9*6, title);
        // Homes aus der Datenbank holen
        List<Home> homes = homeService.getAllHomes(player.getUniqueId(), page);

        if (page > 1 || (page < 3 && !homes.isEmpty())) {
            ItemStack rightArrow = new ItemStack(Material.ARROW);
        }

        // Füge Homes dem Inventar hinzu
        for (Home home : homes) {
            ItemStack homeItem = new ItemStack(Material.PAPER);
            ItemMeta meta = homeItem.getItemMeta();
            meta.setDisplayName(ChatColor.GREEN + ChatColor.BOLD.toString() + home.getHomeName());
            homeItem.setItemMeta(meta);

            gui.addItem(homeItem);
        }

        // Fülle die letzte Zeile mit schwarzem Glas
        ItemStack blackGlass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta glassMeta = blackGlass.getItemMeta();
        glassMeta.setDisplayName(" "); // To remove the name
        glassMeta.addEnchant(Enchantment.DURABILITY, 1, true); // Enchant just for the visual effect
        glassMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS); // Hide the enchant information
        blackGlass.setItemMeta(glassMeta);

        for (int i = 9*5; i < 9*6; i++) {
            gui.setItem(i, blackGlass);
        }

        // Füge den roten Kopf zum Löschen aller Homes hinzu
        ItemStack redHead = new ItemStack(Material.RED_TERRACOTTA);
        ItemMeta redHeadMeta = redHead.getItemMeta();
        redHeadMeta.setDisplayName(ChatColor.RED + ChatColor.BOLD.toString() + "Alle Homes löschen");
        redHead.setItemMeta(redHeadMeta);

        gui.setItem(9*6 - 5, redHead); // Setze den Kopf in den 5. Slot der letzten Reihe

        if (page < 3) {
            ItemStack rightArrow = new ItemStack(Material.ARROW);
            ItemMeta rightMeta = rightArrow.getItemMeta();
            rightMeta.setDisplayName(ChatColor.GREEN + "Nächste Seite");
            rightArrow.setItemMeta(rightMeta);

            gui.setItem(9*6 - 4, rightArrow); // Place right arrow next to the red head
        }

        // Left arrow (previous page)
        if (page > 1) { // Check if the current page is not the first one
            ItemStack leftArrow = new ItemStack(Material.ARROW);
            ItemMeta leftMeta = leftArrow.getItemMeta();
            leftMeta.setDisplayName(ChatColor.GREEN + "Vorherige Seite");
            leftArrow.setItemMeta(leftMeta);

            gui.setItem(9*6 - 6, leftArrow); // Place left arrow next to the red head, on its left
        }

        player.openInventory(gui);
    }

}
