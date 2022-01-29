package me.five.lonia.nms.v1_17_R1;

import me.five.lonia.data.PlayerData;
import me.five.lonia.util.ServerVersion;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundPingPacket;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class NMSManager extends me.five.lonia.nms.NMSManager {

    public NMSManager() {
        super(ServerVersion.v1_18_R1);
    }

    @Override
    public void sendTransaction(Player player, short uid) {
        try {
            ((CraftPlayer)player).getHandle().b.getClass().getMethod("sendPacket", Packet.class).invoke(((CraftPlayer)player).getHandle().b, new ClientboundPingPacket(uid));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Material getBlockType(int blockX, int blockY, int blockZ, Player player) {
        return null;
    }

    @Override
    public void addPacketListener(PlayerData playerData) {
        ((CraftPlayer) playerData.getPlayer()).getHandle().b.a().k.pipeline().addBefore("packet_handler", "Lonia", new PacketListener(playerData));
    }

    @Override
    public void removePacketListener(Player player) {
        PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().b;
        if (playerConnection != null && !playerConnection.isDisconnected()) {
            playerConnection.a().k.pipeline().remove("Lonia");
        }
    }

    @Override
    public double getEntityWidth(Entity entity) {
        try {
            return (double) ((CraftEntity)entity).getHandle().getClass().getMethod("getWidth").invoke(((CraftEntity)entity).getHandle());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public double getEntityHeight(Entity entity) {
        try {
            return (double) ((CraftEntity)entity).getHandle().getClass().getMethod("getHeight").invoke(((CraftEntity)entity).getHandle());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
