package me.five.lonia.transaction;

import me.five.lonia.data.PlayerData;
import me.five.lonia.util.EntityEffectType;
import me.five.lonia.util.Teleport;
import me.five.lonia.util.Ticker;

public class TeleportTransaction extends Transaction {

    private Teleport tp;

    public TeleportTransaction(short uid, Teleport tp) {
        super(uid);
        this.tp = tp;
    }

    @Override
    public void onTransaction(PlayerData data) {

        if (data.getTeleportList().contains(tp)) {
            data.getTeleportList().remove(tp);
            data.getTickerMap().put(Ticker.TELEPORT, 5);
        }

    }

}
