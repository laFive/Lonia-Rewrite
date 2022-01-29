package me.five.lonia.packet.client;

import me.five.lonia.packet.LoniaPacket;
import org.bukkit.inventory.ItemStack;

public class CPacketClickWindow extends LoniaPacket {

    private int windowId;
    private int slotId;
    private int button;
    private short transactionId;
    private int mode;
    
    public CPacketClickWindow(int windowId, int slotId, int button, short transactionId, int mode) {
    
        this.windowId = windowId;
        this.slotId = slotId;
        this.button = button;
        this.transactionId = transactionId;
        this.mode = mode;
    
    }
    
    public int getWindowId() {
        return windowId;
    }
    
    public int getSlotId() {
        return slotId;
    }
    
    public int getButton() {
        return button;
    }
    
    public short getTransactionId() {
        return transactionId;
    }
    
    public int getMode() {
        return mode;
    }

}
