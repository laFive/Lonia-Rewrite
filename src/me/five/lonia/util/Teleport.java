package me.five.lonia.util;

public class Teleport {

    private double x;
    private double y;
    private double z;

    public Teleport(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public boolean locationEquals(CustomLocation location) {

        return (x == location.getPosX() && y == location.getPosY() && z == location.getPosZ());

    }

}
