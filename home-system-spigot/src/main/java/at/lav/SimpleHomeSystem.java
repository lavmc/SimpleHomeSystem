package at.lav;

import at.lav.api.IHomeService;
import at.lav.commands.DeleteHomeCommand;
import at.lav.commands.HomeCommand;
import at.lav.commands.HomesCommand;
import at.lav.commands.SetHomeCommand;
import at.lav.config.Config;
import at.lav.database.Database;
import at.lav.listeners.HomeInventoryListener;
import at.lav.services.HomeServiceSpigot;
import at.lav.utils.HomesGUIManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleHomeSystem extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            Config.setPlugin(this);
            Config.loadConfig();
            Database.initialize();

            // Initialize command executors
            IHomeService homeService = new HomeServiceSpigot();
            HomesGUIManager guiManager = new HomesGUIManager(homeService);

            this.getCommand("sethome").setExecutor(new SetHomeCommand(homeService));
            this.getCommand("home").setExecutor(new HomeCommand(homeService));
            this.getCommand("delhome").setExecutor(new DeleteHomeCommand(homeService));
            this.getCommand("homes").setExecutor(new HomesCommand(guiManager));

            this.getServer().getPluginManager().registerEvents(new HomeInventoryListener(homeService,guiManager), this);

        }catch (Exception e) {
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
