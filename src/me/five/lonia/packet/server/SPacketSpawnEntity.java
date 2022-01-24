package me.five.lonia.packet.server;

import me.five.lonia.packet.LoniaPacket;

import java.util.UUID;

public class SPacketSpawnEntity extends LoniaPacket {

    private int entityId;
    private UUID playerId;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private boolean player;

    public SPacketSpawnEntity(int entityId, UUID playerId, double x, double y, double z, float yaw, float pitch, boolean player) {
        this.entityId = entityId;
        this.playerId = playerId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.player = player;
    }

    public int getEntityId() {
        return entityId;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public boolean isPlayer() {
        return player;
    }

}
