package me.five.lonia.util;

import java.util.Arrays;

public enum ClientVersion {

    v1_7_10(5),
    v1_8(47),
    v1_9(107),
    v1_9_1(108),
    v1_9_2(109),
    v1_9_4(110),
    v1_10_2(210),
    v1_11(315),
    v1_11_2(316),
    v1_12(335),
    v1_12_1(338),
    v1_12_2(340),
    v1_13(393),
    v1_13_1(401),
    v1_13_2(404),
    v1_14(477),
    v1_14_1(480),
    v1_14_2(485),
    v1_14_3(490),
    v1_14_4(498),
    v1_15(573),
    v1_15_1(575),
    v1_15_2(578),
    v1_16(735),
    v1_16_1(736),
    v1_16_2(751),
    v1_16_3(753),
    v1_16_4(754),
    v1_16_5(754),
    v1_17(755),
    v1_17_1(756),
    v1_18(757),
    v1_18_1(757);

    private int versionNumber;

    private int getVersionNumber() {
        return versionNumber;
    }

    public boolean isNewerOrEqual(ClientVersion otherVer) {
        return otherVer.getVersionNumber() >= versionNumber;
    }

    public boolean isOlderOrEqual(ClientVersion otherVer) {
        return otherVer.getVersionNumber() <= versionNumber;
    }

    public static ClientVersion getFromVersionNumber(int pn) {
        return Arrays.stream(values()).filter(v -> v.getVersionNumber() == pn).findFirst().orElse(ClientVersion.v1_17);
    }

    ClientVersion(int i) {
        versionNumber = i;
    }

}
