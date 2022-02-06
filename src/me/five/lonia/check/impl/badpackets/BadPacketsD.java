package me.five.lonia.check.impl.badpackets;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketBlockPlace;
import me.five.lonia.packet.client.CPacketFlying;

public class BadPacketsD extends Check {

    private long lastFlying;
    private boolean flagged;

    public BadPacketsD() {
        super("BadPackets", "D", 3, 8, true);
        setDescription("Checks for block places after the client tick (Scaffold/Autoblock)");
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketFlying) {

            long lastFlying = this.lastFlying;
            this.lastFlying = System.currentTimeMillis();
            long flyingDelay = System.currentTimeMillis() - lastFlying;
            if (flagged && flyingDelay > 45L && flyingDelay < 65L) {
                flag(1, "FlyingDelay:" + (System.currentTimeMillis() - lastFlying) + " Version:" + getData().getClientVersion().toString());
            }
            flagged = false;

        }

        if (packet instanceof CPacketBlockPlace) {

            if (System.currentTimeMillis() - lastFlying < 5L) {
                flagged = true;
            }

        }

    }

}
