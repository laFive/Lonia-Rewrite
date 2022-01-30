package me.five.lonia.check.impl.fly;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.util.ClientVersion;
import me.five.lonia.util.EntityEffectType;
import me.five.lonia.util.Ticker;
import org.bukkit.Bukkit;

public class FlyA extends Check {

    private double lastMotionY;

    public FlyA() {
        super("Fly", "A", 0, 20, true);
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
                   // || getData().getTickerMap().getOrDefault(Ticker.UNDER_BLOCK, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.ABNORMAL_VELOCITY, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.DISMOUNT_TICKS, 0) > 0
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
            double difference = Math.abs(motionY - predictedMotionY);

            /*
             * Client motion is clamped at 0.005 for 1.8, 0.003
             * for 1.9+
             */
            if (Math.abs(predictedMotionY) < 0.005) return;

            /*
             * We do this to compensate for 0.03, particularly for when building up.
             * If the motion is predicted to be greater than 0 but the actual motion
             * is less than 0, this could be as a result of 0.03. The impact of this
             * on detection is minimal.
             */
            if (predictedMotionY > 0 && motionY < 0 && Math.abs(motionY) < 0.1) {
                return;
            }

            if (difference > 1E-4) {
                flag(1, "MotionY:" + motionY + " LastMotionY:" + lastMotionY + " Prediction:" + predictedMotionY + " Difference:" + difference + " PosY:" + getData().getLocation().getPosY() + " Version:" + getData().getClientVersion().toString());
                return;
            }

            pass(0.005);

        }

    }

}
