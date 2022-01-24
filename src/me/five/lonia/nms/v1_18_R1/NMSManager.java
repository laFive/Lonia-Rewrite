package me.five.lonia.nms.v1_18_R1;

import me.five.lonia.data.PlayerData;
import me.five.lonia.util.ServerVersion;
import net.minecraft.network.protocol.game.ClientboundPingPacket;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NMSManager extends me.five.lonia.nms.NMSManager {

    public NMSManager() {
        super(ServerVersion.v1_18_R1);
    }

    @Override
    public void sendTransaction(Player player, short uid) {
        ((CraftPlayer)player).getHandle().b.a(new ClientboundPingPacket(uid));
    }

    @Override
    public Material getBlockType() {
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

}
