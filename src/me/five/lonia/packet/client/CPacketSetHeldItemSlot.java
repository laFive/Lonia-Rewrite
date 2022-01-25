package me.five.lonia.packet.client;

import me.five.lonia.packet.LoniaPacket;

public class CPacketSetHeldItemSlot extends LoniaPacket {

    private int slot;

    public CPacketSetHeldItemSlot(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

}
