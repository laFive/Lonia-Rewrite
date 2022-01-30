package me.five.lonia.command;

import me.five.lonia.command.impl.AlertsCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private List<BaseCommand> registeredCommands;

    public CommandManager() {
        registeredCommands = new ArrayList<>();
        registeredCommands.add(new AlertsCommand());
    }

    public boolean handleCommand(CommandSender sender, String label, String[] args) {

        for (BaseCommand c : registeredCommands) {

            if (c.getCommand().equalsIgnoreCase(label)) {

                return c.processCommand(sender, label, args);

            }

        }

        return false;

    }

}
