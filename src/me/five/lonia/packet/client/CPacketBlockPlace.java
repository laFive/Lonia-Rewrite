package me.five.lonia.packet.client;

import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.util.LoniaBlockLocation;
import me.five.lonia.util.PlayerHand;
import org.bukkit.inventory.ItemStack;

public class CPacketBlockPlace extends LoniaPacket {

    private PlayerHand hand;
    private LoniaBlockLocation blockPos;
    private int placeDirection;
    private float facingX;
    private float facingY;
    private float facingZ;

    public CPacketBlockPlace(PlayerHand hand) {
        this.hand = hand;
    }

    public CPacketBlockPlace(PlayerHand hand, LoniaBlockLocation blockPos, int placeDirection, float facingX, float facingY, float facingZ) {
        this.hand = hand;
        this.blockPos = blockPos;
        this.placeDirection = placeDirection;
        this.facingX = facingX;
        this.facingY = facingY;
        this.facingZ = facingZ;
    }

    public LoniaBlockLocation getBlockPos() {
        return blockPos;
    }

    public int getPlaceDirection() {
        return placeDirection;
    }

    public float getFacingX() {
        return facingX;
    }

    public float getFacingY() {
        return facingY;
    }

    public float getFacingZ() {
        return facingZ;
    }

    public PlayerHand getHand() {
        return hand;
    }

}
