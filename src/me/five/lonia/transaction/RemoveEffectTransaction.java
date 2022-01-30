package me.five.lonia.transaction;

import me.five.lonia.data.PlayerData;
import me.five.lonia.util.EntityEffectType;

public class RemoveEffectTransaction extends Transaction {

    private EntityEffectType effectType;

    public RemoveEffectTransaction(short uid, EntityEffectType effectType) {
        super(uid);
        this.effectType = effectType;
    }

    @Override
    public void onTransaction(PlayerData data) {

        if (data.getActiveEffects().containsKey(effectType)) {

            data.getActiveEffects().get(effectType).setTicks(7);

        }

    }

}
