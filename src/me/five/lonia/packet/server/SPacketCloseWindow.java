package me.five.lonia.packet.server;

import me.five.lonia.packet.LoniaPacket;

public class SPacketCloseWindow extends LoniaPacket {
  
  private int windowId;
  
  public SPacketCloseWindow(int windowId) {
    this.windowId = windowId;
  }
  
  public int getWindowId() {
    return windowId;
  }

}
