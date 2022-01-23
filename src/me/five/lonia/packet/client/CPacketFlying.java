package me.five.lonia.packet.client;

import me.five.lonia.packet.LoniaPacket;

public class CPacketFlying extends LoniaPacket {

    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private boolean onGround;
    private boolean hasPos;
    private boolean hasLook;

    public CPacketFlying(boolean onGround) {
        this.onGround = onGround;
    }

    public CPacketFlying(float yaw, float pitch, boolean onGround) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
        this.hasLook = true;
    }

    public CPacketFlying(double x, double y, double z, boolean onGround) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.onGround = onGround;
        this.hasPos = true;
    }

    public CPacketFlying(double x, double y, double z, float yaw, float pitch, boolean onGround) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
        this.hasPos = true;
        this.hasLook = true;
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

    public boolean isOnGround() {
        return onGround;
    }

    public boolean hasPos() {
        return hasPos;
    }

    public boolean hasLook() {
        return hasLook;
    }
}
