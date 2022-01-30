package me.five.lonia.packet.server;

import me.five.lonia.packet.LoniaPacket;

public class SPacketEntityMove extends LoniaPacket {

    private int entityId;
    private double distanceX;
    private double distanceY;
    private double distanceZ;
    private float yaw;
    private float pitch;
    private boolean onGround;
    private boolean hasRot;
    private boolean hasPos;

    public SPacketEntityMove(int entityId, double x, double y, double z, boolean onGround) {
        this.entityId = entityId;
        this.distanceX = x;
        this.distanceY = y;
        this.distanceZ = z;
        this.onGround = onGround;
        this.hasPos = true;
    }

    public SPacketEntityMove(int entityId, double x, double y, double z, float yaw, float pitch, boolean onGround) {
        this.entityId = entityId;
        this.distanceX = x;
        this.distanceY = y;
        this.distanceZ = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
        this.hasPos = true;
        this.hasRot = true;
    }

    public SPacketEntityMove(int entityId, float yaw, float pitch, boolean onGround) {
        this.entityId = entityId;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
        this.hasRot = true;
    }

    public int getEntityId() {
        return entityId;
    }

    public double getDistanceX() {
        return distanceX;
    }

    public double getDistanceY() {
        return distanceY;
    }

    public double getDistanceZ() {
        return distanceZ;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public boolean hasRot() {
        return hasRot;
    }

    public boolean hasPos() {
        return hasPos;
    }

}
