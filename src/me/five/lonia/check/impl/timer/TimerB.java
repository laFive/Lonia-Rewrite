package me.five.lonia.check.impl.timer;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.util.ClientVersion;
import me.five.lonia.util.Ticker;

import java.util.ArrayList;
import java.util.List;

public class TimerB extends Check {

    private long lastLag;
    private int threshold;
    private Long lastSend;
    private List<Long> packetDelays;

    public TimerB() {
        super("Timer", "B", 3, 10, true);
        setDescription("Checks for invalid tick delays (Timer/Regen)");
        lastSend = System.currentTimeMillis();
        packetDelays = new ArrayList<>();
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketFlying) {

            if (getData().isRidingEntity() || (getData().getClientVersion().isNewerOrEqual(ClientVersion.v1_17) && getData().getLocation().positionEquals(getData().getLastLocation()))) return;

            if (getData().isTeleporting()) {
                packetDelays.clear();
                threshold = 0;
                return;
            }

            if (lastSend != null) {

                long delay = System.currentTimeMillis() - lastSend;
                if ((delay < 15L || delay > 100L) || getData().getTickerMap().getOrDefault(Ticker.WORLD_LOADED, 0) < 200) {
                    lastLag = System.currentTimeMillis();
                    packetDelays.clear();
                    threshold = 0;
                    lastSend = System.currentTimeMillis();
                    return;
                }

                if (System.currentTimeMillis() - lastLag < 2500L) {
                    lastSend = System.currentTimeMillis();
                    return;
                }

                packetDelays.add(delay);
                if (packetDelays.size() > 20)
                    packetDelays.remove(0);

                if (packetDelays.size() >= 20) {

                    long averageDelay = 0;
                    for (long del : packetDelays)
                        averageDelay += del;
                    averageDelay = averageDelay / packetDelays.size();
                    if (averageDelay >= 43L) {
                        pass(0.001);
                        threshold = Math.max(0, --threshold);
                        lastSend = System.currentTimeMillis();
                        return;
                    }

                    if (++threshold > 3) {

                        flag(1, "Threshold:" + threshold + " Average:" + averageDelay);
                        threshold = 0;
                    }

                }

            }

            lastSend = System.currentTimeMillis();

        }

    }

}
