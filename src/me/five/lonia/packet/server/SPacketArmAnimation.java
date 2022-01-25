package me.five.lonia.packet.server;

import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.util.PlayerHand;

public class SPacketArmAnimation extends LoniaPacket {

    private PlayerHand hand;

    public SPacketArmAnimation(PlayerHand hand) {
        this.hand = hand;
    }

    public PlayerHand getHand() {
        return hand;
    }

}
