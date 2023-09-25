package at.lav;

import at.lav.commands.DeleteHomeCommand;
import at.lav.commands.HomeCommand;
import at.lav.commands.SetHomeCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleHomeSystem extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("delhome").setExecutor(new DeleteHomeCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
