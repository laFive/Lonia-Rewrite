package me.five.lonia.util;

public class LoniaAbilities {

    private boolean isInvulnerable;
    private boolean flying;
    private boolean allowFlying;
    private boolean instantBuild;
    private float flySpeed;
    private float walkSpeed;

    public LoniaAbilities() {}

    public LoniaAbilities(boolean isInvulnerable, boolean flying, boolean allowFlying, boolean instantBuild, float flySpeed, float walkSpeed) {
        this.isInvulnerable = isInvulnerable;
        this.flying = flying;
        this.allowFlying = allowFlying;
        this.instantBuild = instantBuild;
        this.flySpeed = flySpeed;
        this.walkSpeed = walkSpeed;
    }

    public boolean isInvulnerable() {
        return isInvulnerable;
    }

    public void setInvulnerable(boolean invulnerable) {
        isInvulnerable = invulnerable;
    }

    public boolean isFlying() {
        return flying;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    public boolean isAllowFlying() {
        return allowFlying;
    }

    public void setAllowFlying(boolean allowFlying) {
        this.allowFlying = allowFlying;
    }

    public boolean isInstantBuild() {
        return instantBuild;
    }

    public void setInstantBuild(boolean instantBuild) {
        this.instantBuild = instantBuild;
    }

    public float getFlySpeed() {
        return flySpeed;
    }

    public void setFlySpeed(float flySpeed) {
        this.flySpeed = flySpeed;
    }

    public float getWalkSpeed() {
        return walkSpeed;
    }

    public void setWalkSpeed(float walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

}
