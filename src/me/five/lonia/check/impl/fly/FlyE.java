package me.five.lonia.check.impl.fly;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.util.Ticker;

public class FlyE extends Check {

    private double lastMotionY;

    public FlyE() {
        super("Fly", "E", 0, 6, true);
        setDescription("Checks for invalid falling motion (NoFall/Fly)");
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof CPacketFlying) {

            if (!getData().getLocation().isOnGround() && getData().getLastLocation().isOnGround()) {

                double motionY = getData().getLocation().getPosY() - getData().getLastLocation().getPosY();
                double lastMotionY = this.lastMotionY;
                this.lastMotionY = motionY;
                double maxAirDifference = (lastMotionY - 0.08D) * 0.98D;

                if (getData().isTeleporting() || getData().isFlying() || getData().isRidingEntity()
                        || getData().getTickerMap().getOrDefault(Ticker.WORLD_LOADED, 0) < 200
                        || getData().getTickerMap().getOrDefault(Ticker.RIPTIDE, 0) > 0
                        || getData().getTickerMap().getOrDefault(Ticker.GLIDING, 0) > 0
                        || getData().getTickerMap().getOrDefault(Ticker.ABNORMAL_VELOCITY, 0) > 0) {
                    return;
                }

                if (motionY < 0.0 && lastMotionY < 0.0 && motionY < maxAirDifference) {
                    flag(1, "MotionY:" + motionY + " LastMotionY:" + lastMotionY + " MaxAirDif:" + maxAirDifference + " Difference:" + Math.abs(motionY - maxAirDifference));
                    return;
                }

                pass(1E-3);

            }

        }

    }

}
