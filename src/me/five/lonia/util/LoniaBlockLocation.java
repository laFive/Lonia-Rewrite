package me.five.lonia.util;

import me.five.lonia.Lonia;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class LoniaBlockLocation {

    private int blockX;
    private int blockY;
    private int blockZ;

    public LoniaBlockLocation(int blockX, int blockY, int blockZ) {
        this.blockX = blockX;
        this.blockY = blockY;
        this.blockZ = blockZ;
    }


    public Material getType(Player player) {
        return Lonia.getInstance().getNMSManager().getBlockType(blockX, blockY, blockZ, player);
    }

    public int getBlockX() {
        return blockX;
    }

    public int getBlockY() {
        return blockY;
    }

    public int getBlockZ() {
        return blockZ;
    }

}
