package me.five.lonia.check.impl.timer;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.packet.server.SPacketPosition;
import me.five.lonia.util.ClientVersion;
import org.bukkit.Bukkit;

public class TimerA extends Check {

    private long lastFlying;
    private long balance;
    private boolean extendedNoFlying;

    public TimerA() {
        super("Timer", "A", 5, 15, true);
        setDescription("Checks for invalid tick delays (Timer/Regen)");
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketFlying) {

            if (getData().getClientVersion().isNewerOrEqual(ClientVersion.v1_17) && getData().getLocation().positionEquals(getData().getLastLocation())) return;

            long lastFlying = this.lastFlying;
            this.lastFlying = System.currentTimeMillis();
            if (lastFlying == 0L) return;
            long delay = System.currentTimeMillis() - lastFlying;
            balance += (50 - delay);
            if (extendedNoFlying && delay > 30L && delay < 100L && balance < -800L) balance = 0;
            extendedNoFlying = false;
            if (delay > 400L) extendedNoFlying = true;

            if (balance > 110L) {

                flag(1, "Balance:" + balance + " Delay:" + delay + " Version:" + getData().getClientVersion().toString());
                while (balance >= 50) balance -= 50;
                return;

            }

            if (balance <= 0L) pass(0.001);

        }

        if (packet instanceof SPacketPosition) {

            balance -= 50;

        }

    }

}
