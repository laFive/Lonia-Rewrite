package me.five.lonia.listener;

import me.five.lonia.Lonia;
import me.five.lonia.data.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BukkitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Lonia.getInstance().getDataManager().registerPlayer(e.getPlayer());
        PlayerData playerData = Lonia.getInstance().getDataManager().getData(e.getPlayer().getUniqueId());
        if (playerData == null) {
            e.getPlayer().kickPlayer(ChatColor.RED + "Unable to load your playerdata object");
            return;
        }
        Lonia.getInstance().getNMSManager().addPacketListener(playerData);
        Lonia.getInstance().getCheckManager().addChecksToData(playerData);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Lonia.getInstance().getDataManager().removePlayer(e.getPlayer().getUniqueId());
    }

}
