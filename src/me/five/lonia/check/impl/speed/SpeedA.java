package me.five.lonia.check.impl.speed;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.util.ClientVersion;
import me.five.lonia.util.EntityEffectType;
import me.five.lonia.util.Ticker;

public class SpeedA extends Check {

    private double lastMotion;

    public SpeedA() {
        super("Speed", "A", 6, 16, true);
        setDescription("Checks for invalid air friction (Bhop/Speed/Fly)");
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketFlying) {

            if (getData().getClientVersion().isNewerOrEqual(ClientVersion.v1_17) && getData().getLocation().positionEquals(getData().getLastLocation())) return;

            double motionX = getData().getLocation().getPosX() - getData().getLastLocation().getPosX();
            double motionZ = getData().getLocation().getPosZ() - getData().getLastLocation().getPosZ();
            double motion = Math.sqrt((motionX * motionX) + (motionZ * motionZ));
            double lastMotion = this.lastMotion;
            this.lastMotion = motion;

            if (getData().getTickerMap().getOrDefault(Ticker.VELOCITY_TICK, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.STAIRS, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.RIPTIDE, 0) > 0
                    || getData().getActiveEffects().containsKey(EntityEffectType.LEVITATION)
                    || getData().getActiveEffects().containsKey(EntityEffectType.SLOW_FALLING)
                    || getData().isTeleporting() || getData().isFlying() || getData().isRidingEntity() || getData().getAirTicks() < 2) {
                return;
            }

            double predictedMotionXZ = lastMotion * 0.91D;
            double difference = Math.abs(motion - predictedMotionXZ);

            if (motion > predictedMotionXZ && difference > 0.027 && (lastMotion > 6E-2 || motion > 6E-2)) {

                flag(1, "Motion:" + motion + " LastMotion:" + lastMotion + " Prediction:" + predictedMotionXZ + " Difference:" + difference + " Version:" + getData().getClientVersion().toString());
                return;

            }

            pass(1E-4);

        }

    }

}
