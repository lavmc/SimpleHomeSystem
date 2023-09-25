package at.lav.services;

import at.lav.api.IHomeService;
import at.lav.database.Database;
import org.bukkit.Location;

import java.sql.PreparedStatement;
import java.util.UUID;

public class HomeServiceSpigot implements IHomeService {
    @Override
    public void setHome(UUID playerUuid, String homeName, Location location) {
        try {
            PreparedStatement stmt = Database.getConnection().prepareStatement("INSERT INTO homes (player_uuid, home_name, x, y, z, world) VALUES (?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE x = ?, y = ?, z = ?, world = ?");
            stmt.setString(1, playerUuid.toString());
            stmt.setString(2, homeName);
            stmt.setDouble(3, location.getX());
            stmt.setDouble(4, location.getY());
            stmt.setDouble(5, location.getZ());
            stmt.setString(6, location.getWorld().getName());
            stmt.setDouble(7, location.getX());
            stmt.setDouble(8, location.getY());
            stmt.setDouble(9, location.getZ());
            stmt.setString(10, location.getWorld().getName());
            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
