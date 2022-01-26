package me.five.lonia.packet.server;

public class SPacketCloseWindow extends LoniaPacket {
  
  private int windowId;
  
  public SPacketCloseWindow(int windowId) {
  
    this.windowId = windowId;
  
  }
  
  public int getWindowId() {
    return windowId;
  }

}
