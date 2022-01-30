package me.five.lonia.util;

import me.five.lonia.Lonia;
import me.five.lonia.data.PlayerData;
import org.bukkit.Bukkit;

public class TickRunnable implements Runnable {

    @Override
    public void run() {
        for (PlayerData data : Lonia.getInstance().getDataManager().getTotalData()) {

            Lonia.getInstance().getNMSManager().sendTransaction(data.getPlayer(), data.getTickNumber());
            data.getTransactionMap().put(data.getTickNumber(), System.currentTimeMillis());
            if (data.getTickNumber() == Short.MIN_VALUE) {
                data.setTickNumber((short) 0);
            } else {
                data.setTickNumber((short) (data.getTickNumber() - 1));
            }

        }
    }

    public void schedule() {
        Bukkit.getScheduler().runTaskTimer(Lonia.getInstance(), this, 1L, 1L);
    }

}
