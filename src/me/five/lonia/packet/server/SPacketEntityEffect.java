package me.five.lonia.packet.server;

import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.util.EntityEffectType;

public class SPacketEntityEffect extends LoniaPacket {

    private int entityId;
    private byte effectId;
    private byte effectAmplifier;
    private int effectTicks;

    public SPacketEntityEffect(int entityId, byte effectId, byte effectAmplifier, int effectTicks) {
        this.entityId = entityId;
        this.effectId = effectId;
        this.effectAmplifier = effectAmplifier;
        this.effectTicks = effectTicks;
    }

    public int getEntityId() {
        return entityId;
    }

    public byte getEffectId() {
        return effectId;
    }

    public byte getEffectAmplifier() {
        return effectAmplifier;
    }

    public int getEffectTicks() {
        return effectTicks;
    }

    public EntityEffectType getEffectType() {
        return EntityEffectType.getEffectFromId(effectId);
    }

}
