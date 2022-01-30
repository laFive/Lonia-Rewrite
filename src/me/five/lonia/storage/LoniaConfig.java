package me.five.lonia.storage;

import me.five.lonia.Lonia;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class LoniaConfig {

    private File file;
    private YamlConfiguration yaml;

    public LoniaConfig(File configFile) {
        this.file = configFile;
        yaml = YamlConfiguration.loadConfiguration(configFile);
    }

    public boolean isPlayerExempt(UUID uuid) {
        return ((List<String>)yaml.get("data.exempt-players")).contains(uuid.toString());
    }

    public boolean isPlayerAlerts(UUID uuid) {
        return !((List<String>)yaml.get("data.toggled-player-alerts")).contains(uuid.toString());
    }

    public boolean isAutobans() {
        return (boolean) yaml.get("Lonia.Autobans");
    }

    public boolean isDebugMode() {
        return (boolean) yaml.get("Lonia.Debug-Mode");
    }

    public String getBanCommand() {
        return (String) yaml.get("Lonia.Ban-Command");
    }

    public boolean isCheckEnabled(String check, String subType) {
        if (yaml.get("Checks." + check + "-" + subType + ".enabled") == null) return true;
        return (boolean) yaml.get("Checks." + check + "-" + subType + ".enabled");
    }

    public boolean isCheckAutoban(String check, String subType) {
        if (yaml.get("Checks." + check + "-" + subType + ".autoban") == null) return true;
        return (boolean) yaml.get("Checks." + check + "-" + subType + ".autoban");
    }

    public String getPluginName() {
        return (String) yaml.get("Lonia.Name");
    }

    public String getColor() {
        return (String) yaml.get("Lonia.Color");
    }

    public void setCheckEnabled(String check, String subType, boolean enabled) {
        yaml.set("Checks." + check + "-" + subType + ".enabled", enabled);
        try {
            yaml.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
            Lonia.getInstance().getLogger().info("Unable to save the config! Please scroll up for more details");
        }
    }

    public void setCheckAutoban(String check, String subType, boolean autoban) {
        yaml.set("Checks." + check + "-" + subType + ".autoban", autoban);
        try {
            yaml.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
            Lonia.getInstance().getLogger().info("Unable to save the config! Please scroll up for more details");
        }
    }

    public void setBanCommand(String banCommand) {
        yaml.set("Lonia.Ban-Command", banCommand);
        try {
            yaml.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
            Lonia.getInstance().getLogger().info("Unable to save the config! Please scroll up for more details");
        }
    }

    public void setDebugMode(boolean debug) {
        yaml.set("Lonia.Debug-Mode", debug);
        try {
            yaml.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
            Lonia.getInstance().getLogger().info("Unable to save the config! Please scroll up for more details");
        }
    }

    public void setAutobans(boolean autobans) {
        yaml.set("Lonia.Autobans", autobans);
        try {
            yaml.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
            Lonia.getInstance().getLogger().info("Unable to save the config! Please scroll up for more details");
        }
    }

    public void setExempt(UUID uuid, boolean exempt) {
        List<String> exemptList = (List<String>) yaml.get("data.exempt-players");
        if (!exempt && !exemptList.contains(uuid.toString())) {
            exemptList.add(uuid.toString());
        }
        if (exempt) {
            exemptList.remove(uuid.toString());
        }
        yaml.set("data.exempt-players", exemptList);
        try {
            yaml.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
            Lonia.getInstance().getLogger().info("Unable to save the config! Please scroll up for more details");
        }
    }

    public void setAlerts(UUID uuid, boolean alerts) {
        List<String> alertsList = (List<String>) yaml.get("data.toggled-player-alerts");
        if (!alerts && !alertsList.contains(uuid.toString())) {
            alertsList.add(uuid.toString());
        }
        if (alerts) {
            alertsList.remove(uuid.toString());
        }
        yaml.set("data.toggled-player-alerts", alertsList);
        try {
            yaml.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
            Lonia.getInstance().getLogger().info("Unable to save the config! Please scroll up for more details");
        }
    }

}
