package me.five.lonia.check.impl.fly;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.util.ClientVersion;
import me.five.lonia.util.EntityEffectType;
import me.five.lonia.util.Ticker;

public class FlyA extends Check {

    private double lastMotionY;

    public FlyA() {
        super("Fly", "A", 8, 20, true);
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketFlying) {

            double motionY = getData().getLocation().getPosY() - getData().getLastLocation().getPosY();
            double lastMotionY = this.lastMotionY;
            this.lastMotionY = motionY;

            if (getData().isTeleporting() || getData().isFlying() || getData().isRidingEntity()
                    || getData().getTickerMap().getOrDefault(Ticker.WORLD_LOADED, 0) < 200
                    || getData().getTickerMap().getOrDefault(Ticker.VELOCITY, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.UNDER_BLOCK, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.ABNORMAL_VELOCITY, 0) > 0
                    || getData().getActiveEffects().containsKey(EntityEffectType.LEVITATION)
                    || getData().getActiveEffects().containsKey(EntityEffectType.SLOW_FALLING)
                    || getData().getTickerMap().getOrDefault(Ticker.RIPTIDE, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.GLIDING, 0) > 0
                    || !(!getData().getLocation().isOnGround() && !getData().getLastLocation().isOnGround())) {
                return;
            }

            /*
             * We do this because 1.17+ clients send an additional C06 on right click.
             * This additional packet has the same position as the last sent flying
             * packet.
             */
            if (getData().getClientVersion().isNewerOrEqual(ClientVersion.v1_17) && getData().getLocation().positionEquals(getData().getLastLocation())) return;

            double predictedMotionY = (lastMotionY - 0.08D) * 0.9800000190734863D;

            /*
             * Client motion is clamped at 0.005 for 1.8, 0.003
             * for 1.9+
             */
            double motionClamp = getData().getClientVersion().isNewerOrEqual(ClientVersion.v1_9) ? 0.003 : 0.005;
            if (Math.abs(predictedMotionY) < motionClamp) predictedMotionY = 0;
            double difference = Math.abs(motionY - predictedMotionY);

            /*
             * This fixes 0.03 when onGround true doesn't get sent
             * usually happens when building up
             */
            if (predictedMotionY > 0 && motionY < 0 && Math.abs(motionY) < 0.1) return;

            if (difference > 3E-2) {
                flag(1, "MotionY:" + motionY + " LastMotionY:" + lastMotionY + " Prediction:" + predictedMotionY + " Difference:" + difference + " PosY:" + getData().getLocation().getPosY() + " Version:" + getData().getClientVersion().toString());
                return;
            }

            pass(0.005);

        }

    }

}
