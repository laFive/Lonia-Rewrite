package me.five.lonia.packet.server;

import me.five.lonia.packet.LoniaPacket;

public class SPacketHealth extends LoniaPacket {

    private float health;
    private int food;
    private float foodSaturation;

    public SPacketHealth(float health, int food, float foodSaturation) {
        this.health = health;
        this.food = food;
        this.foodSaturation = foodSaturation;
    }

    public float getHealth() {
        return health;
    }

    public int getFood() {
        return food;
    }

    public float getFoodSaturation() {
        return foodSaturation;
    }

}
