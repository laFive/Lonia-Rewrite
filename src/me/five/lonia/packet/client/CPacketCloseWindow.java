package me.five.lonia.packet.client;

import me.five.lonia.packet.LoniaPacket;

public class CPacketCloseWindow extends LoniaPacket {

  private int windowId;
  
  public CPacketCloseWindow(int windowId) {
      this.windowId = windowId;
  }
  
  public int getWindowId() {
      return this.windowId;
  }

}
