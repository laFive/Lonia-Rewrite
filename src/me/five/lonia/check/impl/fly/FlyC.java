package me.five.lonia.check.impl.fly;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.util.EntityEffectData;
import me.five.lonia.util.EntityEffectType;
import me.five.lonia.util.Ticker;

public class FlyC extends Check {

    public FlyC() {
        super("Fly", "C", 0, 8, true);
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketFlying) {

            if (getData().getLocation().isOnGround()) return;

            if (getData().isTeleporting() || getData().isFlying() || getData().isRidingEntity()
                    || getData().getTickerMap().getOrDefault(Ticker.WORLD_LOADED, 0) < 200
                    || getData().getTickerMap().getOrDefault(Ticker.VELOCITY, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.ABNORMAL_VELOCITY, 0) > 0
                    || getData().getActiveEffects().containsKey(EntityEffectType.LEVITATION)
                    || getData().getTickerMap().getOrDefault(Ticker.RIPTIDE, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.GLIDING, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.STAIRS, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.SLABS, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.LAUNCH_BLOCK, 0) > 0) {
                return;
            }

            float maxMotionY = 0.42f;
            double motionY = getData().getLocation().getPosY() - getData().getLastLocation().getPosY();
            if (getData().getActiveEffects().containsKey(EntityEffectType.JUMP)) {
                EntityEffectData data = getData().getActiveEffects().get(EntityEffectType.JUMP);
                maxMotionY += 0.11 * data.getAmplifier();
            }

            if (motionY > maxMotionY) {
                flag(1, "MotionY:" + motionY + " MaxAllowedMotionY:" + maxMotionY + " AirTicks:" + getData().getAirTicks());
                return;
            }

            pass(1E-3);

        }

    }

}
