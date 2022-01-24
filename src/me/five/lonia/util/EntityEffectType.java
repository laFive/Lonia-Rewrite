package me.five.lonia.util;

import java.util.Arrays;

public enum EntityEffectType {

    SPEED((byte) 1),
    SLOWNESS((byte) 2),
    HASTE((byte) 3),
    MINING_FATIGUE((byte) 4),
    STRENGTH((byte) 5),
    JUMP((byte) 8),
    NAUSEA((byte) 9),
    REGENERATION((byte) 10),
    RESISTANCE((byte) 11),
    FIRE_RESISTANCE((byte) 12),
    WATER_BREATHING((byte) 13),
    INVISIBILITY((byte) 14),
    BLINDNESS((byte) 15),
    NIGHT_VISION((byte) 16),
    HUNGER((byte) 17),
    WEAKNESS((byte) 18),
    POISON((byte) 19),
    WITHER((byte) 20),
    HEALTH_BOOST((byte) 21),
    ABSORPTION((byte) 22),
    SATURATION((byte) 23),
    GLOWING((byte) 24),
    LEVITATION((byte) 25),
    LUCK((byte) 26),
    UNLUCK((byte) 27),
    SLOW_FALLING((byte) 28),
    CONDUIT_POWER((byte) 29),
    DOLPHINS_GRACE((byte) 30),
    BAD_OMEN((byte) 31);

    private byte id;

    EntityEffectType(byte id) {
        this.id = id;
    }

    public byte getId() {
        return id;
    }

    public static EntityEffectType getEffectFromId(byte id) {
        return Arrays.stream(EntityEffectType.values()).filter(ef -> ef.getId() == id).findFirst().orElse(null);
    }

}
