package me.five.lonia.util;

public enum ServerVersion {

    v1_8_R3(0),
    v1_18_R1(1);

    private int versionNumber;

    private int getVersionNumber() {
        return versionNumber;
    }

    ServerVersion(int i) {
        versionNumber = i;
    }

}
