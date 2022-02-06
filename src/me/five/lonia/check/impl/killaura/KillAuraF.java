package me.five.lonia.check.impl.killaura;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketUseEntity;
import me.five.lonia.util.Ticker;

public class KillAuraF extends Check {

    public KillAuraF() {
        super("KillAura", "F", 0, 5, true);
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketUseEntity) {

            CPacketUseEntity useEntityPacket = (CPacketUseEntity) packet;
            if (!getData().isUsingItem() || getData().getTickerMap().getOrDefault(Ticker.USE_ITEM, 0) > 0) return;
            flag(1, "Action:" + useEntityPacket.getAction().toString() + " Version:" + getData().getClientVersion().toString());

        }

    }

}
