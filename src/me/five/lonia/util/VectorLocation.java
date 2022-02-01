package me.five.lonia.util;

import me.five.lonia.Lonia;
import org.bukkit.entity.Entity;

public class VectorLocation {

    private double x;
    private double y;
    private double z;

    public VectorLocation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Cuboid toBoundingBox(Entity entity) {

        double height = Lonia.getInstance().getNMSManager().getEntityHeight(entity);
        double width = Lonia.getInstance().getNMSManager().getEntityWidth(entity);
        return new Cuboid(x - width / 2, x + width / 2, y, y + height, z - width / 2, z + width / 2);

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

}
