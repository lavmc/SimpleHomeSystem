package at.lav.api;

import java.util.List;
import java.util.UUID;
import org.bukkit.Location;

public interface IHomeService {

    void setHome(UUID playerUuid, String homeName, Location location);

    boolean deleteHome(UUID playerUuid, String homeName);

    Location getHome(UUID playerUuid, String homeName);

    List<Location> getAllHomes(UUID playerUuid, int page, int homesPerPage);
}