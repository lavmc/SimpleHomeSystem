package at.lav.api;

import java.util.List;
import java.util.UUID;

import at.lav.model.Home;
import org.bukkit.Location;

public interface IHomeService {

    void setHome(UUID playerUuid, String homeName, Location location);

    boolean deleteHome(UUID playerUuid, String homeName);

    Location getHome(UUID playerUuid, String homeName);

    List<Home> getAllHomes(UUID playerUuid, int page);

    void deleteAllHomes(UUID uniqueId);
}