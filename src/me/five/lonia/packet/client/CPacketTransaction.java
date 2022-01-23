package me.five.lonia.packet.client;

import me.five.lonia.packet.LoniaPacket;

public class CPacketTransaction extends LoniaPacket {

    private int windowId;
    private short uid;
    private boolean accepted;

    public CPacketTransaction(int windowId, short uid, boolean accepted) {
        this.windowId = windowId;
        this.uid = uid;
        this.accepted = accepted;
    }

    public int getWindowId() {
        return windowId;
    }

    public short getUid() {
        return uid;
    }

    public boolean isAccepted() {
        return accepted;
    }
}
