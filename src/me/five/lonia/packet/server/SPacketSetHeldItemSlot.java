package me.five.lonia.packet.server;

import me.five.lonia.packet.LoniaPacket;

public class SPacketSetHeldItemSlot extends LoniaPacket {

    private int slot;

    public SPacketSetHeldItemSlot(int slot) {
        this.slot = slot;
    }

    public int getSlot() {
        return slot;
    }

}
