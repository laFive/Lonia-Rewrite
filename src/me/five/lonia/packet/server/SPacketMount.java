package me.five.lonia.packet.server;

import me.five.lonia.packet.LoniaPacket;

public class SPacketMount extends LoniaPacket {

    private int entityId;
    private int[] passengerIds;

    public SPacketMount(int entityId, int[] passengerIds) {
        this.entityId = entityId;
        this.passengerIds = passengerIds;
    }

    public int getEntityId() {
        return entityId;
    }

    public int[] getPassengerIds() {
        return passengerIds;
    }
}
