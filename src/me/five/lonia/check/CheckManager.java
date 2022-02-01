package me.five.lonia.check;

import me.five.lonia.check.impl.fly.FlyA;
import me.five.lonia.check.impl.fly.FlyB;
import me.five.lonia.check.impl.fly.FlyC;
import me.five.lonia.check.impl.fly.FlyD;
import me.five.lonia.check.impl.fly.FlyE;
import me.five.lonia.check.impl.killaura.KillAuraA;
import me.five.lonia.check.impl.killaura.KillAuraB;
import me.five.lonia.check.impl.killaura.KillAuraC;
import me.five.lonia.check.impl.killaura.KillAuraD;
import me.five.lonia.check.impl.scaffold.ScaffoldA;
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
        checks.add(new FlyB());
        checks.add(new FlyC());
        checks.add(new FlyD());
        checks.add(new FlyE());
        checks.add(new KillAuraA());
        checks.add(new KillAuraB());
        checks.add(new KillAuraC());
        checks.add(new KillAuraD());
        checks.add(new ScaffoldA());
        return checks;
    }

    public void addChecksToData(PlayerData data) {
        for (Check c : getChecks()) {
            if (c.getCheckVersions().equals(EnumCheckVersions.ALL)) {
                c.setPlayerData(data);
                data.getLoadedChecks().add(c);
                continue;
            }
            if (c.getCheckVersions().equals(EnumCheckVersions.LEGACY) && data.getClientVersion().isOlderOrEqual(ClientVersion.v1_8)) {
                c.setPlayerData(data);
                data.getLoadedChecks().add(c);
                continue;
            }
            if (c.getCheckVersions().equals(EnumCheckVersions.NEWCOMBAT) && data.getClientVersion().isNewerOrEqual(ClientVersion.v1_9)) {
                c.setPlayerData(data);
                data.getLoadedChecks().add(c);
                continue;
            }
        }
    }

}
