package me.five.lonia.check.impl.killaura;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.packet.client.CPacketUseEntity;
import me.five.lonia.util.ClientVersion;

public class KillAuraC extends Check {

    private long lastFlying;
    private boolean flagged;
    private boolean sent;

    public KillAuraC() {
        super("KillAura", "C", 0, 5, true);
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketFlying) {

            long lastFlying = this.lastFlying;
            this.lastFlying = System.currentTimeMillis();
            long flyingDelay = System.currentTimeMillis() - lastFlying;

            if (flagged) {
                if (getData().getClientVersion().isNewerOrEqual(ClientVersion.v1_9) && flyingDelay > 45L && flyingDelay < 65L) {
                    flag(1, "FlyingDelay:" + flyingDelay + " Version:" + getData().getClientVersion().toString());
                } else if (getData().getClientVersion().isOlderOrEqual(ClientVersion.v1_8)) {
                    flag(1, "FlyingDelay:" + flyingDelay + " Version:" + getData().getClientVersion().toString());
                }
                flagged = false;
            } else if (sent) pass(1E-2);
            sent = false;

        }

        if (packet instanceof CPacketUseEntity) {
            if (sent) flagged = true;
            sent = true;
        }

    }

}
