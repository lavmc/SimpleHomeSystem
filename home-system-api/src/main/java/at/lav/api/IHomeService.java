package at.lav.api;

import java.util.UUID;
import org.bukkit.Location;

public interface IHomeService {

    void setHome(UUID playerUuid, String homeName, Location location);

    // Additional methods for getHome, deleteHome, etc.
}