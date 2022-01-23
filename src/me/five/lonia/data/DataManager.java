package me.five.lonia.data;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataManager {

    private Map<UUID, PlayerData> playerDataMap;

    public DataManager() {
        playerDataMap = new HashMap<>();
    }

    public void registerPlayer(Player player) {
        playerDataMap.put(player.getUniqueId(), new PlayerData(player));
    }

    public void removePlayer(UUID uuid) {
        playerDataMap.remove(uuid);
    }

    public PlayerData getData(UUID uuid) {
        return playerDataMap.getOrDefault(uuid, null);
    }

}
