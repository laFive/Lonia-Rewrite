package me.five.lonia.util;

import java.util.Arrays;

public enum GameMode {

    SURVIVAL(0),
    CREATIVE(1),
    ADVENTURE(2),
    SPECTATOR(3);

    private int id;

    GameMode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static GameMode getFromId(int id) {
        return Arrays.stream(GameMode.values()).filter(ef -> ef.getId() == id).findFirst().orElse(null);
    }

}
