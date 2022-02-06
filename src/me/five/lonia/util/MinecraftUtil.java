package me.five.lonia.util;

import me.five.lonia.Lonia;
import org.bukkit.entity.Entity;

public class MinecraftUtil {

    public static VectorLocation getLook(float yaw, float pitch) {
        float f = (float) Math.cos(-yaw * 0.017453292F - (float)Math.PI);
        float f1 = (float) Math.sin(-yaw * 0.017453292F - (float)Math.PI);
        float f2 = (float) -Math.cos(-pitch * 0.017453292F);
        float f3 = (float) Math.sin(-pitch * 0.017453292F);
        return new VectorLocation((double)(f1 * f2), (double)f3, (double)(f * f2));
    }

    public static VectorLocation getEyeVector(VectorLocation location, Entity entity) {
        double height = Lonia.getInstance().getNMSManager().getEntityHeight(entity);
        return new VectorLocation(location.getX(), location.getY() + (0.85 * height), location.getZ());
    }

    public static boolean isUseItem(LoniaBlockLocation blockLocation) {

        if (blockLocation.getBlockX() != 0 && blockLocation.getBlockX() != -1) return false;
        if (blockLocation.getBlockY() != 0 && blockLocation.getBlockY() != -1) return false;
        if (blockLocation.getBlockZ() != 0 && blockLocation.getBlockZ() != -1) return false;
        return true;

    }

}
