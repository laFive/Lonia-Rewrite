package me.five.lonia.packet.server;

import me.five.lonia.packet.LoniaPacket;

public class SPacketExplosion extends LoniaPacket {

    private double x;
    private double y;
    private double z;
    private float power;
    private float motionX;
    private float motionY;
    private float motionZ;

    public SPacketExplosion(double x, double y, double z, float power, float motionX, float motionY, float motionZ) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.power = power;
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
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

    public float getPower() {
        return power;
    }

    public float getMotionX() {
        return motionX;
    }

    public float getMotionY() {
        return motionY;
    }

    public float getMotionZ() {
        return motionZ;
    }

}
