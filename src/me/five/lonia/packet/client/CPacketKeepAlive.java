package me.five.lonia.packet.client;

import me.five.lonia.packet.LoniaPacket;

public class CPacketKeepAlive extends LoniaPacket {

    private long id;

    public CPacketKeepAlive(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
