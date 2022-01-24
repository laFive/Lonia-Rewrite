package me.five.lonia.util;

public class EntityEffectData {

    private EntityEffectType effectType;
    private int ticks;
    private int amplifier;

    public EntityEffectData(EntityEffectType type, int ticks, int amplifier) {
        this.effectType = type;
        this.ticks = ticks;
        this.amplifier = amplifier;
    }

    public boolean tick() {
        --ticks;
        if (ticks <= 0) return true;
        return false;
    }

    public EntityEffectType getEffectType() {
        return effectType;
    }

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public int getAmplifier() {
        return amplifier;
    }
}
