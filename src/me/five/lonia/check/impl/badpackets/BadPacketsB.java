package me.five.lonia.check.impl.badpackets;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketBlockDig;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.util.ClientVersion;
import me.five.lonia.util.PlayerDigAction;

public class BadPacketsB extends Check {

    private int streak;
    private long lastFlying;
    private boolean flagged;
    private int flyingCounter;

    public BadPacketsB() {
        super("BadPackets", "B", 0, 3, true);
        setDescription("Checks for invalid item/block packets (Autoblock/Nuker/Blink)");
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (getData().isRidingEntity()) return;

        if (packet instanceof CPacketFlying) {

            if (getData().getClientVersion().isOlderOrEqual(ClientVersion.v1_8)) {
                if (flyingCounter++ > 2) {
                    streak = 0;
                    flyingCounter = 0;
                }
                return;
            }

            long lastFlying = this.lastFlying;
            this.lastFlying = System.currentTimeMillis();
            long flyingDelay = System.currentTimeMillis() - lastFlying;

            if (flyingDelay < 45L || flyingDelay > 65L) {
                flyingCounter = 0;
                streak = 0;
                flagged = false;
                return;
            }

            if (flagged) {
                streak = 0;
                flyingCounter = 0;
                flag(1, "FlyingDelay:" + flyingDelay + " Version:" + getData().getClientVersion().toString());
                return;
            }

            if (flyingCounter++ > 2) {
                streak = 0;
                flyingCounter = 0;
            }

        }

        if (packet instanceof CPacketBlockDig) {

            CPacketBlockDig blockDigPacket = (CPacketBlockDig) packet;
            if (blockDigPacket.getAction() != PlayerDigAction.RELEASE_USE_ITEM && blockDigPacket.getAction() != PlayerDigAction.STOP_DESTROY_BLOCK) return;
            if (streak++ > 1) {
                if (getData().getClientVersion().isNewerOrEqual(ClientVersion.v1_9)) {
                    flagged = true;
                    return;
                }
                flag(1, "Streak:" + streak + " Version:" + getData().getClientVersion().toString());
            }

        }

    }

}
