package me.five.lonia;

import me.five.lonia.data.DataManager;
import me.five.lonia.listener.BukkitListener;
import me.five.lonia.nms.NMSManager;
import me.five.lonia.util.ServerVersion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Lonia extends JavaPlugin {

    private NMSManager nmsManager;
    private static Lonia instance;
    private DataManager dataManager;

    @Override
    public void onEnable() {

        instance = this;
        long start = System.currentTimeMillis();
        try {
            this.nmsManager = NMSManager.create();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            getLogger().info("Unsupported server version detected!");
            getLogger().info("If you believe this is an error, please contact us!");
            getLogger().info("Plugin shutting down...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        this.dataManager = new DataManager();
        registerEvents();
        getLogger().info("Plugin enabled in " + (System.currentTimeMillis() - start) + "ms.");

    }

    @Override
    public void onDisable() {

        getLogger().info("Plugin enabled!");

    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new BukkitListener(), this);
    }

    public static Lonia getInstance() {
        return instance;
    }

    public NMSManager getNMSManager() {
        return nmsManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }



}
