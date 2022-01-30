package me.five.lonia.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class BaseCommand {

    private String command;
    private String permission;
    private String usage;
    private boolean denyConsole;

    public BaseCommand(String command, String permission, String usage, boolean denyConsole) {
        this.command = command;
        this.permission = permission;
        this.usage = usage;
        this.denyConsole = denyConsole;
    }

    public boolean processCommand(CommandSender sender, String label, String[] args) {

        if (!label.equalsIgnoreCase(command)) return false;
        if (!(sender instanceof Player) && denyConsole) return true;
        if (!sender.hasPermission(permission)) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }
        if (!runCommand(sender, label, args)) {
            sender.sendMessage(ChatColor.RED + "Usage: " + usage);
        }
        return true;

    }

    public abstract boolean runCommand(CommandSender sender, String label, String[] args);

    public String getCommand() {
        return command;
    }

    public String getPermission() {
        return permission;
    }

    public String getUsage() {
        return usage;
    }

    public boolean isDenyConsole() {
        return denyConsole;
    }
}
