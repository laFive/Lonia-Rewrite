package me.five.lonia.data;

import me.five.lonia.data.processor.PacketInProcessor;
import me.five.lonia.data.processor.PacketOutProcessor;
import org.bukkit.entity.Player;

public class PlayerData {

    private Player player;
    private PacketInProcessor packetInProcessor;
    private PacketOutProcessor packetOutProcessor;
    
    public PlayerData(Player player) {
        this.player = player;
        this.packetInProcessor = new PacketInProcessor(this);
        this.packetOutProcessor = new PacketOutProcessor(this);
    }

    public Player getPlayer() {
        return player;
    }

    public PacketInProcessor getPacketInProcessor() {
        return packetInProcessor;
    }

    public PacketOutProcessor getPacketOutProcessor() {
        return packetOutProcessor;
    }
}
