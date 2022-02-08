package me.five.lonia.check.impl.speed;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.util.EntityEffectType;
import me.five.lonia.util.Ticker;

public class SpeedB extends Check {

    private double lastMotion;
    private static double SPRINT_SPEED_MULTIPLIER = 2.865;
    private static double WALK_SPEED_MULTIPLIER = 2.35;
    private static double SNEAK_MULTIPLIER = 1;

    public SpeedB() {
        super("Speed", "B", 5, 15, true);
        setDescription("Checks for invalid movement on ground (Speed/TP)");
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketFlying) {

            if (!((CPacketFlying) packet).hasPos() || getData().getLocation().positionEquals(getData().getLastLocation())) return;

            double motionX = getData().getLocation().getPosX() - getData().getLastLocation().getPosX();
            double motionZ = getData().getLocation().getPosZ() - getData().getLastLocation().getPosZ();
            double motion = Math.sqrt((motionX * motionX) + (motionZ * motionZ));
            double lastMotion = this.lastMotion;
            this.lastMotion = motion;

            if (getData().getLocation().isOnGround()) {

                double multiplier;
                if (getData().isSprinting() || getData().getTickerMap().getOrDefault(Ticker.STOP_SPRINT, 0) > 0) {
                    multiplier = SPRINT_SPEED_MULTIPLIER;
                } else if (!getData().isSneaking()) {
                    multiplier = WALK_SPEED_MULTIPLIER;
                } else {
                    multiplier = SNEAK_MULTIPLIER;
                }
                double allowed = getData().getLoniaAbilities().getWalkSpeed() * multiplier;
                allowed = getData().getActiveEffects().containsKey(EntityEffectType.SPEED)
                        ? (allowed + (getData().getActiveEffects().get(EntityEffectType.SPEED).getAmplifier() + 1) * 0.08f)
                        : allowed;
                double lastMotionAllowance = (lastMotion * 0.91) + allowed;
                allowed = getData().getGroundTicks() <= 8 ? Math.max(allowed, lastMotionAllowance) : allowed;
                double difference = Math.abs(motion - allowed);
                if (difference < 0.03 || motion < allowed) {
                    pass(0.001);
                    return;
                }

                flag(1, "Motion:" + motion + " LastMotion:" + lastMotion + " Difference:" + difference + " Allowed:" + allowed + "Version:" + getData().getClientVersion().toString());

            }


        }

    }

}
