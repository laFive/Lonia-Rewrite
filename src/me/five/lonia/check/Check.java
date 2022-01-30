package me.five.lonia.check;

import me.five.lonia.Lonia;
import me.five.lonia.data.PlayerData;
import me.five.lonia.packet.LoniaPacket;
import me.five.lonia.util.EnumCheckVersions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Check {

    private String type;
    private String subType;
    private double violationLevel;
    private int minAlertVl;
    private int minBanVl;
    private boolean enabled;
    private boolean autoban;
    private PlayerData data;
    private EnumCheckVersions checkVersions;

    public Check(String type, String subType, int alertVl, int banVl, boolean autoban) {
        this.type = type;
        this.subType = subType;
        this.minAlertVl = alertVl;
        this.minBanVl = banVl;
        this.enabled = true;
        this.autoban = autoban;
        this.checkVersions = EnumCheckVersions.ALL;
    }

    public Check(String type, String subType, int alertVl, int banVl, boolean autoban, EnumCheckVersions versions) {
        this.type = type;
        this.subType = subType;
        this.minAlertVl = alertVl;
        this.minBanVl = banVl;
        this.enabled = true;
        this.autoban = autoban;
        this.checkVersions = versions;
    }

    public void handle(LoniaPacket packet) {}

    public void flag(double count, String verbose) {
        this.enabled = Lonia.getInstance().getLoniaConfig().isCheckEnabled(type, subType);
        this.autoban = Lonia.getInstance().getLoniaConfig().isCheckAutoban(type, subType);
        if (!enabled) return;
        if (Lonia.getInstance().getLoniaConfig().isPlayerExempt(data.getPlayer().getUniqueId())) return;
        violationLevel += count;
        try {
            log(verbose);
        } catch (IOException exception) {
            exception.printStackTrace();
            Lonia.getInstance().getLogger().info("Failed to save a log file! Please scroll up for more details");
        }
        if (violationLevel >= minAlertVl) {
            int friendlyVl = (int) Math.floor(violationLevel);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!player.hasPermission("lonia.alerts")) continue;
                if (!Lonia.getInstance().getLoniaConfig().isPlayerAlerts(player.getUniqueId())) continue;
                player.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.translateAlternateColorCodes('&',
                        Lonia.getInstance().getLoniaConfig().getColor()) + ChatColor.BOLD +
                        Lonia.getInstance().getLoniaConfig().getPluginName() + ChatColor.DARK_GRAY +
                        "] " + ChatColor.GOLD + data.getPlayer().getName() + ChatColor.GRAY + " has failed " +
                        ChatColor.GOLD + type + " " + subType + ChatColor.GRAY + " (x" + friendlyVl + ")");
            }
        }
        if (violationLevel >= minBanVl && !data.isBanned()) {
            if (!autoban) return;
            if (!Lonia.getInstance().getLoniaConfig().isAutobans()) return;
            data.setBanned(true);
            Bukkit.getScheduler().runTask(Lonia.getInstance(), new Runnable() {
                @Override
                public void run() {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Lonia.getInstance().getLoniaConfig().getBanCommand().replaceAll("%player%", data.getPlayer().getName()));
                }
            });
            Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "-------------------------------------------");
            Bukkit.broadcastMessage(ChatColor.RED.toString() + ChatColor.BOLD + "✗ "  + ChatColor.translateAlternateColorCodes('&', Lonia.getInstance().getLoniaConfig().getColor()) + Lonia.getInstance().getLoniaConfig().getPluginName() + " " + ChatColor.GRAY + "has removed " + ChatColor.translateAlternateColorCodes('&', Lonia.getInstance().getLoniaConfig().getColor()) + data.getPlayer().getName() + ChatColor.GRAY + " from the server.");
            Bukkit.broadcastMessage(ChatColor.RED.toString() + ChatColor.BOLD + "✗ "  + ChatColor.GRAY + "Reason " + ChatColor.DARK_GRAY + "»" + ChatColor.translateAlternateColorCodes('&', Lonia.getInstance().getLoniaConfig().getColor()) + " Cheating");
            Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "-------------------------------------------");
        }
    }

    public void pass(double amount) {
        violationLevel = Math.max(0, violationLevel - amount);
    }

    public void log(String verbose) throws IOException {

        File directory = new File(Lonia.getInstance().getDataFolder() + "/logs/");
        directory.mkdirs();
        File logFile = new File(directory.getPath() + "/" + data.getPlayer().getUniqueId().toString() + ".yml");
        if (!logFile.exists())
            logFile.createNewFile();
        Date date = new Date(System.currentTimeMillis());
        String dateString = "[" + date.getMonth() + 1 + "/" + date.getDate() + "/" + (date.getYear() + 1900) + " " + date.getHours() + ":" + date.getMinutes() + "] ";
        StringBuilder logEntry = new StringBuilder();
        logEntry.append(dateString + type + " " + subType + " " + ((int)Math.floor(violationLevel)) + "Vl" + " Ping:(" + data.getKeepAlivePing() + ") Verbose:(" + verbose + ")");
        FileWriter fw = new FileWriter(logFile, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(logEntry);
        pw.flush();
        pw.close();
        fw.close();

    }

    public void setPlayerData(PlayerData data) {
        this.data = data;
    }

    public PlayerData getData() {
        return data;
    }

    public EnumCheckVersions getCheckVersions() {
        return checkVersions;
    }
}
