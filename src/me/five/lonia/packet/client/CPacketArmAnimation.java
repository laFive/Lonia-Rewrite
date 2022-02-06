package me.five.lonia.packet.client;

import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.util.PlayerHand;

public class CPacketArmAnimation extends LoniaPacket {

    private PlayerHand hand;

    public CPacketArmAnimation(PlayerHand hand) {
        this.hand = hand;
    }

    public PlayerHand getHand() {
        return hand;
    }

}
