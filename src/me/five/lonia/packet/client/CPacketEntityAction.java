package me.five.lonia.packet.client;

import me.five.lonia.packet.LoniaPacket;

public class CPacketEntityAction extends LoniaPacket {

    private Action entityAction;

    public CPacketEntityAction(int actionId) {
        this.entityAction = Action.values()[actionId];
    }

    public Action getAction() {
        return entityAction;
    }

    public static enum Action {
        PRESS_SHIFT_KEY,
        RELEASE_SHIFT_KEY,
        STOP_SLEEPING,
        START_SPRINTING,
        STOP_SPRINTING,
        START_RIDING_JUMP,
        STOP_RIDING_JUMP,
        OPEN_INVENTORY,
        START_FALL_FLYING;
    }

}
