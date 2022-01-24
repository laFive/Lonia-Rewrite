package me.five.lonia.packet.server;

import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.util.GameMode;

public class SPacketRespawn extends LoniaPacket {

    private GameMode gameMode;

    public SPacketRespawn(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

}


