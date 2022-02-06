package me.five.lonia.check.impl.killaura;

import me.five.lonia.check.Check;
import me.five.lonia.data.tracker.EntityTracker;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.packet.client.CPacketUseEntity;
import me.five.lonia.util.Cuboid;
import me.five.lonia.util.MinecraftUtil;
import me.five.lonia.util.UseEntityAction;
import me.five.lonia.util.VectorLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class KillAuraD extends Check {

    private int threshold;
    private boolean attacked;
    private EntityTracker attackedEntity;

    public KillAuraD() {
        super("KillAura", "D", 0, 8, true);
        setDescription("Checks for attacks outside of the target hitbox (KillAura/Hitbox)");
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
                attackedEntity.getRecentLocations(4).stream().forEach(l -> entityLocations.add(l.toBoundingBox(attackedEntity.getEntity()).expand(0.2, 0.2, 0.2)));

                VectorLocation eyePos = MinecraftUtil.getEyeVector(getData().getLocation().toVectorLocation(), getData().getPlayer());
                VectorLocation lookVector = MinecraftUtil.getLook(getData().getLocation().getYaw(), getData().getLocation().getPitch());
                List<VectorLocation> traceVectors = new ArrayList<>();
                for (double d = 0; d < 6D; d += 0.1) {
                    traceVectors.add(new VectorLocation(eyePos.getX() + lookVector.getX() * d,
                            eyePos.getY() + lookVector.getY() * d, eyePos.getZ() + lookVector.getZ() * d));
                }

                AtomicBoolean trace = new AtomicBoolean(false);
                entityLocations.forEach(l -> {
                    for (VectorLocation tr : traceVectors) {
                        if (l.intersectsWithVector(tr)) trace.set(true);
                    }
                });

                if (!trace.get()) {
                    if (++threshold > 1) flag(1, "Threshold:" + threshold + " EntityType:" + attackedEntity.getEntity().getType().toString() + " Version:" + getData().getClientVersion().toString());
                    return;
                }

                threshold = Math.max(0, --threshold);
                pass(0.001);

            }

        }

    }

}
