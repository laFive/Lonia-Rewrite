package me.five.lonia.check;

import me.five.lonia.check.impl.fly.FlyA;
import me.five.lonia.data.PlayerData;
import me.five.lonia.util.ClientVersion;
import me.five.lonia.util.EnumCheckVersions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CheckManager {

    private Collection<Check> getChecks() {
        List<Check> checks = new ArrayList<>();
        checks.add(new FlyA());
        return checks;
    }

    public void addChecksToData(PlayerData data) {
        for (Check c : getChecks()) {
            if (c.getCheckVersions().equals(EnumCheckVersions.ALL)) {
                c.setPlayerData(data);
                data.getLoadedChecks().add(c);
                return;
            }
            if (c.getCheckVersions().equals(EnumCheckVersions.LEGACY) && data.getClientVersion().isOlderOrEqual(ClientVersion.v1_8)) {
                c.setPlayerData(data);
                data.getLoadedChecks().add(c);
                return;
            }
            if (c.getCheckVersions().equals(EnumCheckVersions.NEWCOMBAT) && data.getClientVersion().isNewerOrEqual(ClientVersion.v1_9)) {
                c.setPlayerData(data);
                data.getLoadedChecks().add(c);
                return;
            }
        }
    }

}
