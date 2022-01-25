package me.five.lonia.util;

import java.util.Arrays;

public enum PlayerHand {

    MAIN(0),
    OFFHAND(1);

    private int id;

    PlayerHand(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static PlayerHand getFromId(int id) {
        return Arrays.stream(PlayerHand.values()).filter(ef -> ef.getId() == id).findFirst().orElse(null);
    }

}
