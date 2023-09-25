package at.lav.listeners;


import at.lav.api.IHomeService;
import at.lav.utils.HomesGUIManager;
import at.lav.utils.MessageUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.UUID;

public class HomeInventoryListener implements Listener {

    private final IHomeService homeService;
    private final HomesGUIManager guiManager;

    public HomeInventoryListener(IHomeService homeService, HomesGUIManager guiManager) {
        this.homeService = homeService;
        this.guiManager = guiManager;
    }

    private final HashMap<UUID, Integer> playerPages = new HashMap<>();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();

        if (inv != null && ChatColor.stripColor(event.getView().getTitle()).contains("Ihre Homes")) {
            event.setCancelled(true);

            int currentPage = playerPages.getOrDefault(player.getUniqueId(), 1);

            if (event.getCurrentItem() != null) {
                Material clickedType = event.getCurrentItem().getType();
                String clickedName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());

                if (clickedType == Material.RED_TERRACOTTA) {
                    homeService.deleteAllHomes(player.getUniqueId());
                    player.closeInventory();
                    MessageUtils.sendPrefixedMessage(player,ChatColor.RED + "Alle Homes wurden gelöscht!");
                }else if (clickedType == Material.PAPER) {
                    Location location = homeService.getHome(player.getUniqueId(), clickedName);
                    player.closeInventory();

                    if(location != null) {
                        player.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), 3);
                        player.getWorld().spawnParticle(Particle.ENCHANTMENT_TABLE, player.getLocation(), 200, 0.5, 0.5, 0.5, 2);

                        player.teleport(location);

                        player.getWorld().spawnParticle(Particle.DRAGON_BREATH, location, 100);
                        player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, location, 200, 0.5, 0.5, 0.5, 2);

                        MessageUtils.sendPrefixedMessage(player, ChatColor.GREEN + "Willkommen bei "+clickedName+"!");
                    } else {
                        MessageUtils.sendPrefixedMessage(player, ChatColor.RED + "Dieses Zuhause kann nicht gefunden werden!");
                    }
                } else if (clickedType == Material.ARROW && clickedName.equals("Nächste Seite")) {
                    currentPage++;
                    guiManager.openHomesGUI(player, currentPage);
                    playerPages.put(player.getUniqueId(), currentPage);
                } else if (clickedType == Material.ARROW && clickedName.equals("Vorherige Seite")) {
                    currentPage--;
                    guiManager.openHomesGUI(player, currentPage);
                    playerPages.put(player.getUniqueId(), currentPage);
                }
            }
        }
    }
}
