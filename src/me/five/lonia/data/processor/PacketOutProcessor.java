package me.five.lonia.data.processor;

import me.five.lonia.data.PlayerData;
import me.five.lonia.packet.LoniaPacket;

public class PacketOutProcessor {

    private PlayerData playerData;

    public PacketOutProcessor(PlayerData data) {
        this.playerData = data;
    }

    public void processPacket(LoniaPacket packet) {

    }

}
