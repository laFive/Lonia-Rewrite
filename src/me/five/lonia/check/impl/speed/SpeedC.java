package me.five.lonia.check.impl.speed;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.util.EntityEffectType;
import me.five.lonia.util.Ticker;

public class SpeedC extends Check {

    private static double JUMP_MULTIPLIER = 6.2f;
    private static double FAST_AIR_MULTIPLIER = 3.8f;
    private static double AIR_MULTIPLIER = 3.5f;

    public SpeedC() {
        super("Speed", "C", 4, 12, true);
        setDescription("Checks for invalid movement off ground (Speed/Fly)");
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketFlying) {

            if (!((CPacketFlying) packet).hasPos() || getData().getLocation().positionEquals(getData().getLastLocation())) return;

            double motionX = getData().getLocation().getPosX() - getData().getLastLocation().getPosX();
            double motionZ = getData().getLocation().getPosZ() - getData().getLastLocation().getPosZ();
            double motion = Math.sqrt((motionX * motionX) + (motionZ * motionZ));

            if (getData().isFlying() || getData().isRidingEntity() || getData().isTeleporting()
                    || getData().getTickerMap().getOrDefault(Ticker.GLIDING, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.VELOCITY, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.SLIPPY_BLOCK, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.RIPTIDE, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.UNDER_BLOCK, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.COLLISION, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.WORLD_LOADED, 0) < 200
                    || getData().getTickerMap().getOrDefault(Ticker.SLABS, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.STAIRS, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.ABNORMAL_VELOCITY, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.LAUNCH_BLOCK, 0) > 0) return;

            if (!getData().getLocation().isOnGround()) {


                double multiplier = getData().getAirTicks() == 1 ? JUMP_MULTIPLIER : getData().getAirTicks() <= 3 ? FAST_AIR_MULTIPLIER : AIR_MULTIPLIER;
                double allowed = getData().getLoniaAbilities().getWalkSpeed() * multiplier;
                allowed = getData().getActiveEffects().containsKey(EntityEffectType.SPEED) && getData().getActiveEffects().get(EntityEffectType.SPEED).getTicks() > 0
                        ? (allowed + (getData().getActiveEffects().get(EntityEffectType.SPEED).getAmplifier() + 1) * 0.08f)
                        : allowed;
                double difference = Math.abs(motion - allowed);
                if (motion <= allowed || difference < 0.03) {
                    pass(0.001);
                    return;
                }

                flag(1, "Motion:" + motion + " AirTicks:" + getData().getAirTicks() + " Difference:" + difference + " Allowed:" + allowed + "Version:" + getData().getClientVersion().toString());

            }


        }

    }

}
