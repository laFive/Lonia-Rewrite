package me.five.lonia.packet.server;

import me.five.lonia.packet.LoniaPacket;

public class SPacketLevelChunk extends LoniaPacket {

    private int chunkX;
    private int chunkZ;

    public SPacketLevelChunk() {}

    public SPacketLevelChunk(int chunkX, int chunkZ) {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
    }

    public int getChunkX() {
        return chunkX;
    }

    public int getChunkZ() {
        return chunkZ;
    }

}
