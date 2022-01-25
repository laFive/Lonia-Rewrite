package me.five.lonia.data.processor;

import me.five.lonia.data.PlayerData;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketUseEntity;
import org.bukkit.Bukkit;

public class PacketInProcessor {

    private PlayerData playerData;

    public PacketInProcessor(PlayerData data) {
        this.playerData = data;
    }

    public void processPacket(LoniaPacket packet) {

        if (packet instanceof CPacketUseEntity) {

            Bukkit.broadcastMessage(((CPacketUseEntity) packet).getEntityId() + " " + ((CPacketUseEntity) packet).getAction().name());

        }

    }

}
