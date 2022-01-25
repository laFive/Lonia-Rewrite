package me.five.lonia.nms;

import me.five.lonia.data.PlayerData;
import me.five.lonia.util.ServerVersion;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public abstract class NMSManager {

    private ServerVersion version;

    public NMSManager(ServerVersion version) {
        this.version = version;
    }

    public static NMSManager create() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String serverVersion = Bukkit.getServer().getClass().getName().split("\\.")[3];
        Class<? extends NMSManager> versionNMSManager = (Class<? extends NMSManager>) Class.forName(NMSManager.class.getName().replace(NMSManager.class.getSimpleName(), serverVersion + ".NMSManager"));
        return versionNMSManager.newInstance();
    }

    public abstract void sendTransaction(Player player, short uid);

    public abstract Material getBlockType(int blockX, int blockY, int blockZ, World world);

    public abstract void addPacketListener(PlayerData playerData);

    public abstract void removePacketListener(Player player);

    public ServerVersion getVersion() {
        return version;
    }

}
