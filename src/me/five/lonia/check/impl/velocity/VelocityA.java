package me.five.lonia.check.impl.velocity;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.packet.client.CPacketTransaction;
import me.five.lonia.packet.server.SPacketEntityVelocity;
import me.five.lonia.util.ClientVersion;
import me.five.lonia.util.Ticker;

public class VelocityA extends Check {

    private int flying;
    private Short pendingUid;
    private double expectedVelocity;

    public VelocityA() {
        super("Velocity", "A", 0, 12, true);
        setDescription("Checks for incorrect vertical knockback (Velocity)");
    }

    @Override
    public void handle(LoniaPacket packet) {

        if (packet instanceof SPacketEntityVelocity) {

            double velocityY = ((SPacketEntityVelocity) packet).getVelocityY();
            if (velocityY < 0.1D) {
                this.expectedVelocity = 0D;
                return;
            }
            this.expectedVelocity = velocityY;
            this.pendingUid = getData().getTickNumber();
            this.flying = 0;

        }

        if (packet instanceof CPacketTransaction) {

            short uid = ((CPacketTransaction) packet).getUid();
            if (pendingUid != null && pendingUid == uid) pendingUid = null;

        }

        if (packet instanceof CPacketFlying) {

            if (getData().getTickerMap().getOrDefault(Ticker.UNDER_BLOCK, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.DISMOUNT_TICKS, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.RIPTIDE, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.GLIDING, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.COLLISION, 0) > 0
                    || getData().getTickerMap().getOrDefault(Ticker.ABNORMAL_VELOCITY, 0) > 0
                    || getData().isRidingEntity() || getData().isTeleporting() || getData().isFlying()) {
                expectedVelocity = 0D;
                pendingUid = null;
                return;
            }

            if (expectedVelocity < 0.1D || (getData().getClientVersion().isNewerOrEqual(ClientVersion.v1_9)
                    && getData().getLocation().positionEquals(getData().getLastLocation()))) return;

            if (pendingUid == null) flying++;
            double motionY = getData().getLocation().getPosY() - getData().getLastLocation().getPosY();
            if (motionY >= expectedVelocity * 0.9) {
                expectedVelocity = 0D;
                pass(0.01);
                flying = 0;
                return;
            }

            if (flying < 3) return;

            flag(1, "MotionY:" + motionY + " Velocity:" + expectedVelocity);
            expectedVelocity = 0D;
            flying = 0;

        }

    }

}
