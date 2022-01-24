package me.five.lonia.packet.server;

import me.five.lonia.packet.LoniaPacket;

public class SPacketAbilities extends LoniaPacket {

    private boolean isFlying;
    private boolean allowFlying;
    private boolean isInvulnerable;
    private boolean instantBuilding;
    private float flyingSpeed;
    private float walkingSpeed;

    public SPacketAbilities(boolean isFlying, boolean allowFlying, boolean isInvulnerable, boolean instantBuilding, float flyingSpeed, float walkingSpeed) {
        this.isFlying = isFlying;
        this.allowFlying = allowFlying;
        this.isInvulnerable = isInvulnerable;
        this.instantBuilding = instantBuilding;
        this.flyingSpeed = flyingSpeed;
        this.walkingSpeed = walkingSpeed;
    }

    public boolean isFlying() {
        return isFlying;
    }

    public boolean isAllowFlying() {
        return allowFlying;
    }

    public boolean isInvulnerable() {
        return isInvulnerable;
    }

    public boolean isInstantBuilding() {
        return instantBuilding;
    }

    public float getFlyingSpeed() {
        return flyingSpeed;
    }

    public float getWalkingSpeed() {
        return walkingSpeed;
    }

}
