package me.five.lonia.packet.server;

import me.five.lonia.packet.LoniaPacket;

public class SPacketPosition extends LoniaPacket {

    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private boolean dismountVehicle;

    public SPacketPosition(double x, double y, double z, float yaw, float pitch, boolean dismountVehicle) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.dismountVehicle = dismountVehicle;
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

    public boolean isDismountVehicle() {
        return dismountVehicle;
    }

}
