package me.five.lonia.transaction;

import me.five.lonia.data.PlayerData;
import me.five.lonia.util.EntityEffectData;
import me.five.lonia.util.LoniaAbilities;
import me.five.lonia.util.Ticker;

public class EffectTransaction extends Transaction {

    private EntityEffectData effectData;

    public EffectTransaction(short uid, EntityEffectData effectData) {
        super(uid);
        this.effectData = effectData;
    }

    @Override
    public void onTransaction(PlayerData data) {

        if (data.getActiveEffects().containsKey(effectData.getEffectType()) && data.getActiveEffects().get(effectData.getEffectType()).getAmplifier() > effectData.getAmplifier()) return;
        data.getActiveEffects().put(effectData.getEffectType(), effectData);

    }

}
