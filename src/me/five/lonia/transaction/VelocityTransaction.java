package me.five.lonia.transaction;

import me.five.lonia.data.PlayerData;
import me.five.lonia.util.LoniaAbilities;
import me.five.lonia.util.Ticker;

public class VelocityTransaction extends Transaction {

    private int velocityTicks;

    public VelocityTransaction(short uid, int velocityTicks) {
        super(uid);
        this.velocityTicks = velocityTicks;
    }

    @Override
    public void onTransaction(PlayerData data) {

        data.getTickerMap().put(Ticker.VELOCITY, velocityTicks);
        data.getTickerMap().put(Ticker.VELOCITY_TICK, 2);

    }

}
