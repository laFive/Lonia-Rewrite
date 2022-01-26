package me.five.lonia.packet.client;

import org.bukkit.inventory.ItemStack;

public class CPacketClickWindow {

    private int windowId;
    private int slotId;
    private int button;
    private short transactionId;
    private ItemStack stack;
    private int mode;
    
    public CPacketClickWindow(int windowId, int slotId, int button, short transactionId, ItemStack stack, int mode) {
    
        this.windowId = windowId;
        this.slotId = slotId;
        this.button = button;
        this.transactionId = transactionId;
        this.stack = stack;
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
        return transactionid;
    }
    
    public ItemStack getItemStack() }
        return stack;
    }
    
    public int getMode() {
        return mode;
    }

}
