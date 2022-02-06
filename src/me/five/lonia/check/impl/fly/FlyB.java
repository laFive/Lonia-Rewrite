package me.five.lonia.check.impl.fly;

import me.five.lonia.Lonia;
import me.five.lonia.check.Check;
import me.five.lonia.data.tracker.EntityTracker;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.util.*;

public class FlyB extends Check {

    public FlyB() {
        super("Fly", "B", 2, 6, true);
        setDescription("Checks for impossible client ground values (NoFall/Fly)");
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketFlying) {

            boolean serverGround = getData().getLocation().getPosY() % 0.015625 == 0;
            if (getData().getLocation().isOnGround() && !serverGround && ((CPacketFlying) packet).hasPos()) {

                if (getData().isRidingEntity() || getData().getTickerMap().getOrDefault(Ticker.VELOCITY, 0) > 0) return;
                for (EntityTracker tracker : getData().getEntityTrackerManager().getTrackers()) {
                    for (VectorLocation entityLocation : tracker.getRecentLocations(3)) {
                        if (tracker.getEntity() == null) continue;
                        Cuboid entityBB = entityLocation.toBoundingBox(tracker.getEntity());
                        if ((tracker.getEntity().getType().name().equals("BOAT") || tracker.getEntity().getType().name().equals("SHULKER")) && entityBB.intersectsWith(getData().getBoundingBox().expand(0, 0.1, 0))) {
                            return;
                        }
                    }
                }

                flag(1, "PosY:" + getData().getLocation().getPosY() + " GroundTicks:" + getData().getGroundTicks() + " Modulo:" + (getData().getLocation().getPosY() % 0.015625));
                return;

            }

            pass(1E-4);

        }

    }

}
