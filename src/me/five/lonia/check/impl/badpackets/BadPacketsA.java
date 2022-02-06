package me.five.lonia.check.impl.badpackets;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketEntityAction;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.util.ClientVersion;

public class BadPacketsA extends Check {

    private boolean sneak;
    private boolean sprint;
    private long lastFlying;
    private boolean flagged;

    public BadPacketsA() {
        super("BadPackets", "A", 0, 3, true);
        setDescription("Checks for invalid sprint/sneak packets (Printer/Blink)");
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (getData().isRidingEntity()) return;

        if (packet instanceof CPacketFlying) {

            long lastFlying = this.lastFlying;
            this.lastFlying = System.currentTimeMillis();
            long flyingDelay = System.currentTimeMillis() - lastFlying;

            if (flagged) {
                if (getData().getClientVersion().isNewerOrEqual(ClientVersion.v1_9) && flyingDelay > 45L && flyingDelay < 65L) {
                    flag(1, "Sprint:" + sprint + " Sneak:" + sneak + " FlyingDelay:" + flyingDelay + " Version:" + getData().getClientVersion().toString());
                } else if (getData().getClientVersion().isOlderOrEqual(ClientVersion.v1_8)) {
                    flag(1, "Sprint:" + sprint + " Sneak:" + sneak + " FlyingDelay:" + flyingDelay + " Version:" + getData().getClientVersion().toString());
                }
                flagged = false;
            }

            sprint = false;
            sneak = false;

        }

        if (packet instanceof CPacketEntityAction) {

            CPacketEntityAction actionPacket = (CPacketEntityAction) packet;

            if (actionPacket.getAction().equals(CPacketEntityAction.Action.START_SPRINTING)
                    || actionPacket.getAction().equals(CPacketEntityAction.Action.STOP_SPRINTING)) {

                if (sprint) flagged = true;
                sprint = true;

            }

            if (actionPacket.getAction().equals(CPacketEntityAction.Action.PRESS_SHIFT_KEY)
                    || actionPacket.getAction().equals(CPacketEntityAction.Action.RELEASE_SHIFT_KEY)) {

                if (sneak) flagged = true;
                sneak = true;

            }

        }

    }


}
