package me.five.lonia.check.impl.badpackets;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.util.Ticker;

public class BadPacketsF extends Check {

    private boolean flagged;

    public BadPacketsF() {
        super("BadPackets", "F", 0, 8, true);
        setDescription("Checks for sprinting while using an item");
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketFlying) {

            if (!getData().isUsingItem() || getData().getTickerMap().getOrDefault(Ticker.USE_ITEM, 0) > 0) {
                flagged = false;
                return;
            }

            if (flagged) {
                flag(1, "Version:" + getData().getClientVersion().toString());
                flagged = false;
            }

            if (getData().isSprinting()) {
                flagged = true;
                return;
            }

            pass(0.01);

        }

    }

}
