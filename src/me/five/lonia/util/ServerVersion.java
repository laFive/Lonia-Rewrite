package me.five.lonia.util;

import java.util.Arrays;

public enum ServerVersion {

    v1_7_R4(5),
    v1_8_R3(47),
    v1_9_R2(110),
    v1_10_R1(210),
    v1_11_R1(316),
    v1_13_R2(404),
    v1_14_R1(498),
    v1_15_R1(404),
    v1_16_R3(754),
    v1_17_R1(756),
    v1_18_R1(757);

    private int versionNumber;

    private int getVersionNumber() {
        return versionNumber;
    }

    public boolean isNewer(ServerVersion otherVer) {
        return otherVer.getVersionNumber() > versionNumber;
    }

    public boolean isOlder(ServerVersion otherVer) {
        return otherVer.getVersionNumber() < versionNumber;
    }

    public static ServerVersion getFromVersionNumber(int pn) {
        return Arrays.stream(values()).filter(v -> v.getVersionNumber() == pn).findFirst().orElse(ServerVersion.v1_17_R1);
    }

    ServerVersion(int i) {
        versionNumber = i;
    }

}
