package me.five.lonia.packet.server;

import me.five.lonia.packet.LoniaPacket;

public class SPacketOpenWindow extends LoniaPacket {

    private int windowId;
    private String type;
    private String title;
    private int slots;
    private int entityId;

    public SPacketOpenWindow(int windowId, String type, String title, int slots, int entityId) {
        this.windowId = windowId;
        this.type = type;
        this.title = title;
        this.slots = slots;
        this.entityId = entityId;
    }

    public SPacketOpenWindow(int windowId, String title) {
        this.windowId = windowId;
        this.title = title;
    }

    public int getWindowId() {
        return windowId;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public int getSlots() {
        return slots;
    }

    public int getEntityId() {
        return entityId;
    }

}
