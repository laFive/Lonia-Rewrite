package me.five.lonia.check.impl.badpackets;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.util.Ticker;

public class BadPacketsE extends Check {

    public BadPacketsE() {
        super("BadPackets", "E", 0, 1, true);
        setDescription("Checks for impossible move distances (Blink/Disabler/TP)");
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketFlying) {

            if (getData().isTeleporting() || getData().isRidingEntity() || getData().getTickerMap().getOrDefault(Ticker.WORLD_LOADED, 0) < 200) return;

            double motionX = Math.abs(getData().getLocation().getPosX() - getData().getLastLocation().getPosX());
            double motionY = Math.abs(getData().getLocation().getPosY() - getData().getLastLocation().getPosY());
            double motionZ = Math.abs(getData().getLocation().getPosZ() - getData().getLastLocation().getPosZ());

            if (motionX > 8.0D || motionY > 8.0D || motionZ > 8.0D) {

                flag(1, "MotionX:" + motionX + " MotionY:" + motionY + " MotionZ:" + motionZ + " Version:" + getData().getClientVersion().toString());

            }

        }

    }

}
