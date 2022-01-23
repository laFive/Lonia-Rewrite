package me.five.lonia.packet.server;

import me.five.lonia.packet.LoniaPacket;

public class SPacketKeepAlive extends LoniaPacket {

    private long id;

    public SPacketKeepAlive(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
