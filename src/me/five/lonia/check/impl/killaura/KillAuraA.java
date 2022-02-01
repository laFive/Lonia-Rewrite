package me.five.lonia.check.impl.killaura;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.packet.client.CPacketUseEntity;

public class KillAuraA extends Check {

    private int hitTicks;
    private double lastDistance;

    public KillAuraA() {
        super("KillAura", "A", 0, 6, true);
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketUseEntity) {
            hitTicks = 0;
            return;
        }

        if (packet instanceof CPacketFlying) {

            if (getData().getLocation().positionEquals(getData().getLastLocation())) return;

            hitTicks++;
            double diffX = getData().getLocation().getPosX() - getData().getLastLocation().getPosX();
            double diffZ = getData().getLocation().getPosZ() - getData().getLastLocation().getPosZ();
            double distanceSquared = ((diffX * diffX) + (diffZ * diffZ));
            double distance = Math.sqrt(distanceSquared);
            double lastDistance = this.lastDistance;
            this.lastDistance = distance;

            if (!getData().getLocation().isOnGround() || !((CPacketFlying) packet).hasLook() || getData().getLocation().positionEquals(getData().getLastLocation())) return;
            float differenceYaw = getData().getLocation().getYaw() - getData().getLastLocation().getYaw();

            if (Math.abs(differenceYaw) > 2D && distance > 0.18 && Math.abs(distance - lastDistance) < 0.00005) {
                if (hitTicks < 15) flag(1, "YawDif:" + differenceYaw + " Distance:" + distance + " Acceleration:" + Math.abs(distance - lastDistance) + " LastDistance:" + lastDistance);
                return;
            }

            pass(1E-4);


        }

    }

}
