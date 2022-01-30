package me.five.lonia.command.impl;

import me.five.lonia.Lonia;
import me.five.lonia.command.BaseCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AlertsCommand extends BaseCommand {

    public AlertsCommand() {
        super("alerts", "lonia.alerts", "/alerts", true);
    }

    @Override
    public boolean runCommand(CommandSender sender, String label, String[] args) {

        Player p = (Player) sender;
        if (Lonia.getInstance().getLoniaConfig().isPlayerAlerts(p.getUniqueId())) {

            Lonia.getInstance().getLoniaConfig().setAlerts(p.getUniqueId(), false);
            sender.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.translateAlternateColorCodes('&',
                    Lonia.getInstance().getLoniaConfig().getColor()) + ChatColor.BOLD +
                    Lonia.getInstance().getLoniaConfig().getPluginName() + ChatColor.DARK_GRAY +
                    "] " + ChatColor.GRAY + "Your anticheat alerts are now disabled.");
            return true;

        }

        Lonia.getInstance().getLoniaConfig().setAlerts(p.getUniqueId(), true);
        sender.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.translateAlternateColorCodes('&',
                Lonia.getInstance().getLoniaConfig().getColor()) + ChatColor.BOLD +
                Lonia.getInstance().getLoniaConfig().getPluginName() + ChatColor.DARK_GRAY +
                "] " + ChatColor.GRAY + "Your anticheat alerts are now enabled.");

        return true;
    }

}
