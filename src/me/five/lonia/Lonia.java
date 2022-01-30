package me.five.lonia;

import me.five.lonia.check.CheckManager;
import me.five.lonia.command.CommandManager;
import me.five.lonia.data.DataManager;
import me.five.lonia.listener.BukkitListener;
import me.five.lonia.nms.NMSManager;
import me.five.lonia.storage.LoniaConfig;
import me.five.lonia.util.BlockManager;
import me.five.lonia.util.ServerVersion;
import me.five.lonia.util.TickRunnable;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Lonia extends JavaPlugin {

    private NMSManager nmsManager;
    private static Lonia instance;
    private DataManager dataManager;
    private BlockManager blockManager;
    private LoniaConfig loniaConfig;
    private CommandManager commandManager;
    private CheckManager checkManager;

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
        this.blockManager = new BlockManager();
        this.commandManager = new CommandManager();
        this.checkManager = new CheckManager();
        registerEvents();
        new TickRunnable().schedule();
        loadConfig();
        getLogger().info("Plugin enabled in " + (System.currentTimeMillis() - start) + "ms.");

    }

    @Override
    public void onDisable() {

        getLogger().info("Plugin enabled!");

    }

    public void loadConfig() {
        File config = new File(getDataFolder() + "/config.yml");
        if (!config.exists()) {
            saveResource("config.yml", false);
        }
        this.loniaConfig = new LoniaConfig(config);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return commandManager.handleCommand(sender, label, args);
    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new BukkitListener(), this);
    }

    public static Lonia getInstance() {
        return instance;
    }

    public CheckManager getCheckManager() {
        return checkManager;
    }

    public NMSManager getNMSManager() {
        return nmsManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public LoniaConfig getLoniaConfig() {
        return loniaConfig;
    }

    public BlockManager getBlockManager() {
        return blockManager;
    }

}
