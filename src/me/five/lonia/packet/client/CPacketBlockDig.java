package me.five.lonia.packet.client;

import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.util.LoniaBlockLocation;
import me.five.lonia.util.PlayerDigAction;
import net.minecraft.core.BlockPosition;

public class CPacketBlockDig extends LoniaPacket {

    private LoniaBlockLocation blockPos;
    private PlayerDigAction action;

    public CPacketBlockDig(PlayerDigAction action) {
        this.action = action;
    }

    public CPacketBlockDig(LoniaBlockLocation blockPos, PlayerDigAction action) {
        this.blockPos = blockPos;
        this.action = action;
    }

    public LoniaBlockLocation getBlockPos() {
        return blockPos;
    }

    public PlayerDigAction getAction() {
        return action;
    }

}
