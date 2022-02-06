package me.five.lonia.check.impl.badpackets;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketEntityAction;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.util.ClientVersion;

public class BadPacketsC extends Check {

    public BadPacketsC() {
        super("BadPackets", "C", 0, 1, true);
        setDescription("Checks for invalid rotations (Derp)");
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketFlying) {

            if (Math.abs(getData().getLocation().getPitch()) > 90 && !getData().isTeleporting()) {
                flag(1, "Version:" + getData().getClientVersion().toString());
            }

        }

    }


}
