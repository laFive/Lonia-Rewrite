package me.five.lonia.data.processor;

import me.five.lonia.data.PlayerData;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.server.SPacketMount;
import me.five.lonia.packet.server.SPacketRemoveEffect;
import org.bukkit.Bukkit;

public class PacketOutProcessor {

    private PlayerData playerData;

    public PacketOutProcessor(PlayerData data) {
        this.playerData = data;
    }

    public void processPacket(LoniaPacket packet) {

        if (packet instanceof SPacketMount) {

            Bukkit.broadcastMessage(((SPacketMount) packet).getEntityId() + " " + ((SPacketMount) packet).getPassengerIds()[0]);

        }

    }

}
