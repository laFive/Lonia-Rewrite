package me.five.lonia.transaction;

import me.five.lonia.data.PlayerData;
import me.five.lonia.util.LoniaAbilities;
import me.five.lonia.util.Ticker;

public class AbilitiesTransaction extends Transaction {

    private int abilitiesTicks;
    private LoniaAbilities abilities;

    public AbilitiesTransaction(short uid, LoniaAbilities abilities, int abilitiesTicks) {
        super(uid);
        this.abilities = abilities;
        this.abilitiesTicks = abilitiesTicks;
    }

    @Override
    public void onTransaction(PlayerData data) {

        data.setAbilities(abilities);
        data.getTickerMap().put(Ticker.ABILITIES, abilitiesTicks);

    }

}
