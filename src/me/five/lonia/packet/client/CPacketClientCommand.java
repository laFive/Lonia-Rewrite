package me.five.lonia.packet.client;

import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.util.ClientCommand;

public class CPacketClientCommand extends LoniaPacket {

    private ClientCommand clientCommand;

    public CPacketClientCommand(ClientCommand clientCommand) {
        this.clientCommand = clientCommand;
    }

    public ClientCommand getClientCommand() {
        return clientCommand;
    }

}
