package me.five.lonia.packet.client;

import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.util.PlayerHand;

public class CPacketUseItem extends LoniaPacket {

    private PlayerHand hand;

    public CPacketUseItem(PlayerHand hand) {
        this.hand = hand;
    }

    public PlayerHand getHand() {
        return hand;
    }

}
