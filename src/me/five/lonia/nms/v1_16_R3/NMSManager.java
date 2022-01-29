/*
package me.five.lonia.nms.v1_16_R3;

import me.five.lonia.data.PlayerData;
import me.five.lonia.util.ServerVersion;
import net.minecraft.server.v1_16_R3.PacketPlayOutTransaction;
import net.minecraft.server.v1_16_R3.PlayerConnection;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class NMSManager extends me.five.lonia.nms.NMSManager {

    public NMSManager() {
        super(ServerVersion.v1_18_R1);
    }

    @Override
    public void sendTransaction(Player player, short uid) {
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(new PacketPlayOutTransaction(0, uid, false));
    }

    @Override
    public Material getBlockType(int blockX, int blockY, int blockZ, Player player) {
        return null;
    }

    @Override
    public void addPacketListener(PlayerData playerData) {
        ((CraftPlayer) playerData.getPlayer()).getHandle().playerConnection.a().channel.pipeline().addBefore("packet_handler", "Lonia", new PacketListener(playerData));
    }

    @Override
    public void removePacketListener(Player player) {
        PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().playerConnection;
        if (playerConnection != null && !playerConnection.isDisconnected()) {
            playerConnection.a().channel.pipeline().remove("Lonia");
        }
    }

    @Override
    public double getEntityWidth(Entity entity) {
        return ((CraftEntity)entity).getHandle().getWidth();
    }

    @Override
    public double getEntityHeight(Entity entity) {
        return ((CraftEntity)entity).getHandle().getHeight();
    }

}
*/
