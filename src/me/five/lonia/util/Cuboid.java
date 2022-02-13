package me.five.lonia.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collection;

public class Cuboid {

    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private double z1;
    private double z2;

    public Cuboid(double x1, double x2, double y1, double y2, double z1, double z2) {
        this.x1 = Math.min(x1, x2);
        this.x2 = Math.max(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.y2 = Math.max(y1, y2);
        this.z1 = Math.min(z1, z2);
        this.z2 = Math.max(z1, z2);
    }

    public Cuboid() {}

    public Cuboid modify(double x1, double x2, double y1, double y2, double z1, double z2) {

        double modifiedX1 = this.x1 + x1;
        double modifiedX2 = this.x2 + x2;
        double modifiedY1 = this.y1 + y1;
        double modifiedY2 = this.y2 + y2;
        double modifiedZ1 = this.z1 + z1;
        double modifiedZ2 = this.z2 + z2;

        return new Cuboid(modifiedX1, modifiedX2, modifiedY1, modifiedY2, modifiedZ1, modifiedZ2);

    }

    public double distanceXZ(double x, double z) {

        if (containsXZ(x, z)) return 0;

        double minDistX = Math.min(x1 - x, x2 - x);
        double minDistZ = Math.min(z1 - z, z2 - z);
        return Math.sqrt((minDistX * minDistX) + (minDistZ * minDistZ));

    }

    public boolean containsXZ(double x, double z) {
        return x1 <= x && x2 >= x && z1 <= z && z2 >= z;
    }

    public Cuboid expand(double x, double y, double z) {

        return new Cuboid(x1 - x, x2 + x, y1 - y, y2 + y, z1 - z, z2 + z);

    }

    public boolean checkBlocks(Player bukkitPlayer, Collection<Material> types) {

        int x = (int) Math.floor(x1);
        int maxX = (int) Math.ceil(x2);

        while (x < maxX) {

            int y = (int) Math.floor(y1);
            int maxY = (int) Math.ceil(y2);

            while (y < maxY) {

                int z = (int) Math.floor(z1);
                int maxZ = (int) Math.ceil(z2);

                while (z < maxZ) {
                    LoniaBlockLocation blockLoc = new LoniaBlockLocation(x, y, z);
                    if (types.contains(blockLoc.getType(bukkitPlayer))) return true;
                    ++z;
                }
                ++y;
            }
            ++x;
        }

        return false;

    }

    public boolean checkBlocksExcept(Player bukkitPlayer, Collection<Material> types) {

        int x = (int) Math.floor(x1);
        int maxX = (int) Math.ceil(x2);

        while (x < maxX) {

            int y = (int) Math.floor(y1);
            int maxY = (int) Math.ceil(y2);

            while (y < maxY) {

                int z = (int) Math.floor(z1);
                int maxZ = (int) Math.ceil(z2);

                while (z < maxZ) {
                    LoniaBlockLocation blockLoc = new LoniaBlockLocation(x, y, z);
                    if (!types.contains(blockLoc.getType(bukkitPlayer))) return true;
                    ++z;
                }
                ++y;
            }
            ++x;
        }

        return false;

    }

    public boolean intersectsWith(Cuboid other) {
        return other.getX2() > this.getX1() && other.getX1() < this.getX2() ? (other.getY2() > this.getY1() && other.getY1() < this.getY2() ? other.getZ2() > this.getZ1() && other.getZ1() < this.getZ2() : false) : false;
    }

    public boolean intersectsWithVector(VectorLocation vec) {
        if (vec.getX() < x1 || vec.getX() > x2) {
            return false;
        } else if (vec.getY() < y1 || vec.getY() > y2) {
            return false;
        } else if (vec.getZ() < z1 || vec.getZ() > z2) {
            return false;
        }
        return true;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }

    public double getZ1() {
        return z1;
    }

    public double getZ2() {
        return z2;
    }
}