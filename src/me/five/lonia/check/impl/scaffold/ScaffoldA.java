package me.five.lonia.check.impl.scaffold;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketBlockPlace;
import me.five.lonia.packet.client.CPacketFlying;

public class ScaffoldA extends Check {

    private int placeTicks;
    private double lastDistance;

    public ScaffoldA() {
        super("Scaffold", "A", 0, 6, true);
        setDescription("Checks for no slowdown when aiming (Scaffold/Autoblock)");
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketBlockPlace) {
            placeTicks = 0;
            return;
        }

        if (packet instanceof CPacketFlying) {

            placeTicks++;
            double diffX = getData().getLocation().getPosX() - getData().getLastLocation().getPosX();
            double diffZ = getData().getLocation().getPosZ() - getData().getLastLocation().getPosZ();
            double distanceSquared = ((diffX * diffX) + (diffZ * diffZ));
            double distance = Math.sqrt(distanceSquared);
            double lastDistance = this.lastDistance;
            this.lastDistance = distance;

            if (!getData().getLocation().isOnGround() || !((CPacketFlying) packet).hasLook() || getData().getLocation().positionEquals(getData().getLastLocation())) return;
            float differenceYaw = getData().getLocation().getYaw() - getData().getLastLocation().getYaw();

            if (Math.abs(differenceYaw) > 5D && distance > 0.26 && Math.abs(distance - lastDistance) < 0.00005) {
                if (placeTicks < 7) flag(1, "YawDif:" + differenceYaw + " Distance:" + distance + " Acceleration:" + Math.abs(distance - lastDistance) + " LastDistance:" + lastDistance);
                return;
            }

            pass(1E-4);


        }

    }

}
