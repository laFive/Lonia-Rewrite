package me.five.lonia.data;

import com.viaversion.viaversion.api.Via;
import me.five.lonia.Lonia;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataManager {

    private Map<UUID, PlayerData> playerDataMap;

    public DataManager() {
        playerDataMap = new HashMap<>();
    }

    public void registerPlayer(Player player) {
        int ver = Lonia.getInstance().getNMSManager().getVersion().getVersionNumber();
        if (Bukkit.getPluginManager().getPlugin("ViaVersion") != null) {
            ver = Via.getAPI().getPlayerVersion(player.getUniqueId());
        }
        playerDataMap.put(player.getUniqueId(), new PlayerData(player, ver));
    }

    public void removePlayer(UUID uuid) {
        playerDataMap.remove(uuid);
    }

    public PlayerData getData(UUID uuid) {
        return playerDataMap.getOrDefault(uuid, null);
    }

    public Collection<PlayerData> getTotalData() {
        return playerDataMap.values();
    }

}
