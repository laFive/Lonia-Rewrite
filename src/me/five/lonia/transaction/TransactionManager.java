package me.five.lonia.transaction;

import me.five.lonia.data.PlayerData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TransactionManager {

    private PlayerData playerData;
    private List<Transaction> transactionList;

    public TransactionManager(PlayerData data) {
        this.playerData = data;
        this.transactionList = new ArrayList<>();
    }

    public void handleTransaction(short uid) {
        Iterator<Transaction> transactionIterator = transactionList.iterator();
        while (transactionIterator.hasNext()) {
            Transaction tr = transactionIterator.next();
            if (tr.getUid() == uid) {
                tr.onTransaction(playerData);
                transactionIterator.remove();
            }
        }
    }

    public void addTransaction(Transaction t) {
        transactionList.add(t);
    }

}
