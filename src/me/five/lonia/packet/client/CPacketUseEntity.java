package me.five.lonia.packet.client;

import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.util.UseEntityAction;

public class CPacketUseEntity extends LoniaPacket {

    private int entityId;
    private UseEntityAction action;

    public CPacketUseEntity(int entityId, UseEntityAction action) {
        this.entityId = entityId;
        this.action = action;
    }

    public int getEntityId() {
        return entityId;
    }

    public UseEntityAction getAction() {
        return action;
    }

}
