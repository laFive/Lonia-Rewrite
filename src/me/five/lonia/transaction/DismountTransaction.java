package me.five.lonia.transaction;

import me.five.lonia.data.PlayerData;
import me.five.lonia.util.Ticker;

public class DismountTransaction extends Transaction {

    public DismountTransaction(short uid) {
        super(uid);
    }

    @Override
    public void onTransaction(PlayerData data) {
        data.setRidingEntity(false);
        data.getTickerMap().put(Ticker.DISMOUNT_TICKS, 3);
    }

}
