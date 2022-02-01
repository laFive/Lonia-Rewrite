package me.five.lonia.check.impl.killaura;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketBlockPlace;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.packet.client.CPacketUseEntity;
import me.five.lonia.util.EnumCheckVersions;

public class KillAuraB extends Check {

    private boolean sent;

    public KillAuraB() {
        super("KillAura", "B", 0, 3, true, EnumCheckVersions.LEGACY);
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketFlying) {
            sent = false;
        }

        if (packet instanceof CPacketUseEntity && sent) {
            flag(1, "N/A");
        }

        if (packet instanceof CPacketBlockPlace) {
            sent = true;
        }

    }

}
