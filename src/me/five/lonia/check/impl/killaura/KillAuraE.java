package me.five.lonia.checks.impl.killaura;

import me.five.lonia.checks.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.packet.client.CPacketUseEntity;

public class KillAuraE extends Check {
  
  private boolean flagged;
  private long lastFlying;
  
  public KillAuraE() {
    super("KillAura", "E", 0, 8, true);
  }
  
  @Override
  public void handle(LoniaPacket packet) {
   
    if (packet instanceof CPacketFlying) {
     
      long lastFlying = this.lastFlying;
      this.lastFlying = System.currentTimeMillis();
      if (flagged && lastFlying - System.currentTimeMillis() > 45L) {
        flag(1, "FlyingDelay:" + System.currentTimeMillis() - lastFlying;
      }
      flagged = false;
      
    }
    
  }
  
}
