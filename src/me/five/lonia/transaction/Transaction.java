package me.five.lonia.transaction;

import me.five.lonia.data.PlayerData;

public abstract class Transaction {

    private short uid;

    public Transaction(short uid) {
        this.uid = uid;
    }

    public short getUid() {
        return uid;
    }

    public abstract void onTransaction(PlayerData data);

}
