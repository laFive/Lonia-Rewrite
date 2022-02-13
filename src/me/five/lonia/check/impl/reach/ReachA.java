package me.five.lonia.check.impl.reach;

import me.five.lonia.check.Check;
import me.five.lonia.data.tracker.EntityTracker;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.packet.client.CPacketUseEntity;
import me.five.lonia.util.Cuboid;
import me.five.lonia.util.MinecraftUtil;
import me.five.lonia.util.UseEntityAction;
import me.five.lonia.util.VectorLocation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReachA extends Check {

    private int threshold;
    private boolean attacked;
    private EntityTracker attackedEntity;

    public ReachA() {
        super("Reach", "A", 2, 8, true);
        setDescription("Checks for player attack range (Reach)");
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketUseEntity) {

            CPacketUseEntity useEntityPacket = (CPacketUseEntity) packet;
            EntityTracker tracker = getData().getEntityTrackerManager().getTracker(useEntityPacket.getEntityId());
            if (!useEntityPacket.getAction().equals(UseEntityAction.ATTACK) || tracker == null) return;
            attacked = true;
            attackedEntity = tracker;

        }

        if (packet instanceof CPacketFlying) {

            if (attacked) {

                attacked = false;
                List<Cuboid> entityLocations = new ArrayList<>();
                List<Double> distances = new ArrayList<>();
                attackedEntity.getRecentLocations(2).stream().forEach(l -> {
                    Cuboid bb = l.toBoundingBox(attackedEntity.getEntity());
                    double difX = bb.getX2() - bb.getX1();
                    double centerX = bb.getX2() - difX / 2;
                    double difZ = bb.getZ2() - bb.getZ1();
                    double centerZ = bb.getZ2() - difZ / 2;
                    double distX = centerX - getData().getLocation().getPosX();
                    double distZ = centerZ - getData().getLocation().getPosZ();
                    distances.add(bb.distanceXZ(getData().getLocation().getPosX(), getData().getLocation().getPosZ()));
                    distances.add(bb.distanceXZ(getData().getLastLocation().getPosX(), getData().getLastLocation().getPosZ()));
                    distances.add(Math.sqrt((distX * distX) + (distZ * distZ)));
                    distX = centerX - getData().getLocation().getPosX();
                    distZ = bb.getZ1() - getData().getLocation().getPosZ();
                    distances.add(Math.sqrt((distX * distX) + (distZ * distZ)));
                    distX = bb.getX1() - getData().getLocation().getPosX();
                    distZ = centerZ - getData().getLocation().getPosZ();
                    distances.add(Math.sqrt((distX * distX) + (distZ * distZ)));
                    distX = bb.getX2() - getData().getLocation().getPosX();
                    distZ = centerZ - getData().getLocation().getPosZ();
                    distances.add(Math.sqrt((distX * distX) + (distZ * distZ)));
                    distX = centerX - getData().getLocation().getPosX();
                    distZ = bb.getZ2() - getData().getLocation().getPosZ();
                    distances.add(Math.sqrt((distX * distX) + (distZ * distZ)));
                    // Corners
                    distX = bb.getX2() - getData().getLocation().getPosX();
                    distZ = bb.getZ2() - getData().getLocation().getPosZ();
                    distances.add(Math.sqrt((distX * distX) + (distZ * distZ)));
                    distX = bb.getX2() - getData().getLocation().getPosX();
                    distZ = bb.getZ1() - getData().getLocation().getPosZ();
                    distances.add(Math.sqrt((distX * distX) + (distZ * distZ)));
                    distX = bb.getX1() - getData().getLocation().getPosX();
                    distZ = bb.getZ2() - getData().getLocation().getPosZ();
                    distances.add(Math.sqrt((distX * distX) + (distZ * distZ)));
                    distX = bb.getX1() - getData().getLocation().getPosX();
                    distZ = bb.getZ1() - getData().getLocation().getPosZ();
                    distances.add(Math.sqrt((distX * distX) + (distZ * distZ)));
                });

                if (distances.isEmpty()) return;

                double minDistance = distances.stream().mapToDouble(d -> d).min().orElse(0);
                if (minDistance > 3.13D && minDistance < 6.5f) {
                    if ((threshold += 6) >= 12) {
                        flag(1, "Threshold:" + threshold + " Distance:" + minDistance + " TotalDistances:" + distances.size());
                        return;
                    }
                } else if (minDistance < 3.0D && minDistance < 6.5f) {
                    threshold = Math.max(0, --threshold);
                    pass(0.01);
                }

            }

        }

    }

}
