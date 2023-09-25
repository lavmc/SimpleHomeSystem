package at.lav.config;

import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Config {

    private static JSONObject jsonConfig;
    private static JavaPlugin plugin;

    public static void setPlugin(JavaPlugin mainPlugin) {
        plugin = mainPlugin;
    }

    public static void loadConfig() throws Exception {
        File configFile = new File("plugins/configurations/config.json");

        if (!configFile.exists()) {
            createDefaultConfig(configFile);
        }

        try (InputStream is = new FileInputStream(configFile)) {
            byte[] buf = new byte[is.available()];
            int bytesRead = is.read(buf);
            String content = new String(buf, 0, bytesRead, StandardCharsets.UTF_8);
            jsonConfig = new JSONObject(content);
        }

        if (jsonConfig.getString("databaseUser").equals("PLACEHOLDER") ||
                jsonConfig.getString("databasePassword").equals("PLACEHOLDER") ||
                jsonConfig.getString("databaseHost").equals("PLACEHOLDER") ||
                jsonConfig.getString("databaseName").equals("PLACEHOLDER")) {

            // Disable the plugin
            plugin.getLogger().severe("------------------------------------------------------------------------------------");
            plugin.getLogger().severe("Please configure the Database Configuration of Simple Home System inside configurations/config.json");
            plugin.getLogger().severe("------------------------------------------------------------------------------------");


            plugin.setEnabled(false);
        }
    }

    private static void createDefaultConfig(File configFile) throws IOException {
        JSONObject defaultConfig = new JSONObject();
        defaultConfig.put("databaseHost", "PLACEHOLDER");
        defaultConfig.put("databasePort", "PLACEHOLDER");
        defaultConfig.put("databaseUser", "PLACEHOLDER");
        defaultConfig.put("databasePassword", "PLACEHOLDER");
        defaultConfig.put("databaseName", "PLACEHOLDER");

        // Ensure parent directory exists
        configFile.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(configFile)) {
            writer.write(defaultConfig.toString(4));
        }
    }

    public static String getDatabaseUser() {
        return jsonConfig.getString("databaseUser");
    }
    public static String getDatabasePassword() {
        return jsonConfig.getString("databasePassword");
    }
    public static String getDatabaseHost() {
        return jsonConfig.getString("databaseHost");
    }
    public static String getDatabaseName() {
        return jsonConfig.getString("databaseName");
    }
    public static String getDatabasePort() {
        return jsonConfig.getString("databasePort");
    }
}