package me.five.lonia.check.impl.fly;

import me.five.lonia.Lonia;
import me.five.lonia.check.Check;
import me.five.lonia.data.tracker.EntityTracker;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.util.Cuboid;
import me.five.lonia.util.CustomLocation;
import me.five.lonia.util.Ticker;
import me.five.lonia.util.VectorLocation;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Arrays;

public class FlyD extends Check {

    private int threshold;
    private CustomLocation setBackLocation;

    public FlyD() {
        super("Fly", "D", 10, 0, false);
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketFlying) {

            boolean serverGround = getData().getBoundingBox().modify(0, 0, -1, 0, 0, 0)
                    .checkBlocksExcept(getData().getPlayer(), Arrays.asList(Material.AIR));
            boolean clientGround = getData().getLocation().isOnGround();

            if (clientGround && serverGround) {
                setBackLocation = getData().getLocation();
                pass(1E-2);
                threshold = 0;
                return;
            }

            for (EntityTracker tracker : getData().getEntityTrackerManager().getTrackers()) {
                for (VectorLocation entityLocation : tracker.getRecentLocations(3)) {
                    if (tracker.getEntity() == null) return;
                    Cuboid entityBB = entityLocation.toBoundingBox(tracker.getEntity());
                    if ((tracker.getEntity().getType().name().equals("BOAT") || tracker.getEntity().getType().name().equals("SHULKER")) && entityBB.intersectsWith(getData().getBoundingBox().expand(0, 0.1, 0))) {
                        return;
                    }
                }
            }

            if (clientGround && ++threshold > 3 && setBackLocation != null) {
                if (!getData().isRidingEntity() && !getData().isFlying() && !getData().isTeleporting() && getData().getTickerMap().getOrDefault(Ticker.WORLD_LOADED, 0) >= 200) {
                    Location bukkitSetBackLocation = setBackLocation.toBukkitLocation(getData().getPlayer().getWorld());
                    bukkitSetBackLocation.setYaw(getData().getLocation().getYaw());
                    bukkitSetBackLocation.setPitch(getData().getLocation().getPitch());
                    getData().getPlayer().teleport(bukkitSetBackLocation);
                }
            }

        }

    }

}
