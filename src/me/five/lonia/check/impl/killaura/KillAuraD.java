package me.five.lonia.check.impl.killaura;

import me.five.lonia.Lonia;
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

public class KillAuraD extends Check {

    private boolean attacked;
    private List<Cuboid> entityLocations;

    public KillAuraD() {
        super("KillAura", "D", 0, 8, true);
        entityLocations = new ArrayList<>();
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketUseEntity) {

            CPacketUseEntity useEntityPacket = (CPacketUseEntity) packet;
            EntityTracker tracker = getData().getEntityTrackerManager().getTracker(useEntityPacket.getEntityId());
            if (!useEntityPacket.getAction().equals(UseEntityAction.ATTACK) || tracker == null) return;
            entityLocations.clear();
            tracker.getRecentLocations(4).stream().forEach(l -> entityLocations.add(l.toBoundingBox(tracker.getEntity())));
            attacked = true;

        }

        if (packet instanceof CPacketFlying) {

            if (attacked && entityLocations.size() > 3) {

                VectorLocation eyePos = MinecraftUtil.getEyeVector(getData().getLocation().toVectorLocation(), getData().getPlayer());
                VectorLocation lookVector = MinecraftUtil.getLook(getData().getLocation().getYaw(), getData().getLocation().getPitch());
                VectorLocation raytrace = new VectorLocation(eyePos.getX() + lookVector.getX() * 8,
                        eyePos.getY() + lookVector.getY() * 8, eyePos.getZ() + lookVector.getZ() * 8);

                boolean trace = false;

                for (Cuboid c : entityLocations) {

                    boolean x1trace = (c.getX1() < raytrace.getX() && c.getX1() > eyePos.getX()) || (c.getX1() > raytrace.getX() && c.getX1() < eyePos.getX());
                    boolean x2trace = (c.getX2() < raytrace.getX() && c.getX2() > eyePos.getX()) || (c.getX2() > raytrace.getX() && c.getX2() < eyePos.getX());
                    boolean xtrace = x1trace || x2trace;
                    boolean z1trace = (c.getZ1() < raytrace.getZ() && c.getZ1() > eyePos.getZ()) || (c.getZ1() > raytrace.getZ() && c.getZ1() < eyePos.getZ());
                    boolean z2trace = (c.getZ2() < raytrace.getZ() && c.getZ2() > eyePos.getZ()) || (c.getZ2() > raytrace.getZ() && c.getZ2() < eyePos.getZ());
                    boolean ztrace = z1trace || z2trace;
                    if (xtrace && ztrace || c.intersectsWith(getData().getBoundingBox())) trace = true;

                }

                if (!trace) {
                    Bukkit.broadcastMessage(ChatColor.RED + "trol");
                }

            }

            attacked = false;

        }

    }

}
