package me.five.lonia.check.impl.killaura;

import me.five.lonia.check.Check;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.packet.client.CPacketFlying;
import me.five.lonia.packet.client.CPacketUseEntity;

public class KillAuraE extends Check {
  
  private boolean flagged;
  private long lastFlying;
  
  public KillAuraE() {
    super("KillAura", "E", 0, 8, true);
    setDescription("Checks for attacks after the client tick (KillAura/TPAura)");
  }
  
  @Override
  public void handle(LoniaPacket packet) {
   
    if (packet instanceof CPacketFlying) {
     
      long lastFlying = this.lastFlying;
      this.lastFlying = System.currentTimeMillis();
      long flyingDelay = System.currentTimeMillis() - lastFlying;
      if (flagged && flyingDelay > 45L && flyingDelay < 65L) {
        flag(1, "FlyingDelay:" + (System.currentTimeMillis() - lastFlying) + " Version:" + getData().getClientVersion().toString());
      }
      flagged = false;
      
    }

    if (packet instanceof CPacketUseEntity) {

      if (System.currentTimeMillis() - lastFlying < 5L) {
        flagged = true;
      }

    }
    
  }
  
}
