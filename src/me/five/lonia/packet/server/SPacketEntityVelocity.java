package me.five.lonia.packet.server;

import me.five.lonia.packet.LoniaPacket;

public class SPacketEntityVelocity extends LoniaPacket {

    private int entityId;
    private int velocityX;
    private int velocityY;
    private int velocityZ;

    public SPacketEntityVelocity(int entityId, int velocityX, int velocityY, int velocityZ) {
        this.entityId = entityId;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
    }

    public int getEntityId() {
        return entityId;
    }

    public double getVelocityX() {
        return velocityX / 8000.0D;
    }

    public double getVelocityY() {
        return velocityY / 8000.0D;
    }

    public double getVelocityZ() {
        return velocityZ / 8000.0D;
    }

}
