package at.lav.services;

import at.lav.api.IHomeService;
import at.lav.database.Database;
import at.lav.model.Home;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public boolean deleteHome(UUID playerUuid, String homeName) {
        try {
            PreparedStatement checkStmt = Database.getConnection().prepareStatement("SELECT 1 FROM homes WHERE player_uuid = ? AND home_name = ?");
            checkStmt.setString(1, playerUuid.toString());
            checkStmt.setString(2, homeName);

            ResultSet rs = checkStmt.executeQuery();
            if (!rs.next()) {
                return false;
            }

            PreparedStatement deleteStmt = Database.getConnection().prepareStatement("DELETE FROM homes WHERE player_uuid = ? AND home_name = ?");
            deleteStmt.setString(1, playerUuid.toString());
            deleteStmt.setString(2, homeName);
            deleteStmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    @Override
    public Location getHome(UUID playerUuid, String homeName) {
        try {
            PreparedStatement stmt = Database.getConnection().prepareStatement("SELECT x, y, z, world FROM homes WHERE player_uuid = ? AND home_name = ?");
            stmt.setString(1, playerUuid.toString());
            stmt.setString(2, homeName);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double x = rs.getDouble("x");
                double y = rs.getDouble("y");
                double z = rs.getDouble("z");
                String worldName = rs.getString("world");
                World world = Bukkit.getServer().getWorld(worldName);
                if (world == null) {
                    return null;
                }
                return new Location(world, x, y, z);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Home> getAllHomes(UUID playerUuid, int page) {
        List<Home> homes = new ArrayList<>();
        int offset = (page - 1) * 45;

        try {
            PreparedStatement stmt = Database.getConnection().prepareStatement("SELECT x, y, z, world, home_name FROM homes WHERE player_uuid = ? LIMIT ? OFFSET ?");
            stmt.setString(1, playerUuid.toString());
            stmt.setInt(2, 45);
            stmt.setInt(3, offset);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                double x = rs.getDouble("x");
                double y = rs.getDouble("y");
                double z = rs.getDouble("z");
                String name = rs.getString("home_name");
                String worldName = rs.getString("world");
                World world = Bukkit.getServer().getWorld(worldName);
                if (world == null) {
                    return null;
                }
                homes.add(new Home(playerUuid, name, new Location(world, x, y, z)));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return homes;
    }

    @Override
    public void deleteAllHomes(UUID playerUuid) {
        try {
            PreparedStatement stmt = Database.getConnection().prepareStatement("DELETE FROM homes WHERE player_uuid = ?");
            stmt.setString(1, playerUuid.toString());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
