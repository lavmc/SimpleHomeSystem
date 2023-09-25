package at.lav.model;

import org.bukkit.Location;

import java.util.UUID;

public class Home {
    private final UUID playerUuid;
    private final String homeName;
    private final Location location;

    public Home(UUID playerUuid, String homeName, Location location) {
        this.playerUuid = playerUuid;
        this.homeName = homeName;
        this.location = location;
    }

    public String getHomeName() {
        return homeName;
    }

    @Override
    public String toString() {
        return "Home{" +
                "playerUuid=" + playerUuid +
                ", homeName='" + homeName + '\'' +
                ", location=" + location +
                '}';
    }
}
