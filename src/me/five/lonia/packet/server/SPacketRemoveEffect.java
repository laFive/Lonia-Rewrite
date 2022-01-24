package me.five.lonia.packet.server;

import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.util.EntityEffectType;

public class SPacketRemoveEffect extends LoniaPacket {

    private int entityId;
    private byte effectId;

    public SPacketRemoveEffect(int entityId, byte effectId) {
        this.entityId = entityId;
        this.effectId = effectId;
    }

    public int getEntityId() {
        return entityId;
    }

    public byte getEffectId() {
        return effectId;
    }

    public EntityEffectType getEffectType() {
        return EntityEffectType.getEffectFromId(effectId);
    }

}
