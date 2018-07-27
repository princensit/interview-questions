package com.prince.design.vending_machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.prince.design.vending_machine.exception.InsufficientChangeException;
import com.prince.design.vending_machine.exception.ItemNotAvailableException;
import com.prince.design.vending_machine.exception.NotFullPaidException;

import lombok.Getter;

/**
 * @author Prince Raj
 */
public class VendingMachineImpl implements VendingMachine {

    private Inventory<Item> itemInventory = new Inventory<>();

    private Inventory<Coin> coinInventory = new Inventory<>();

    @Getter
    private int totalSales;

    @Getter
    private Item currentItem;

    @Getter
    private long currentBalance;

    public VendingMachineImpl() {
        initialize();
    }

    private void initialize() {
        for (Item item : Item.values()) {
            itemInventory.put(item, 2);
        }

        for (Coin coin : Coin.values()) {
            coinInventory.put(coin, 5);
        }
    }

    @Override
    public int selectItemAndGetPrice(Item item) {
        if (itemInventory.isAvailable(item)) {
            currentItem = item;
            return currentItem.getPrice();
        } else {
            throw new ItemNotAvailableException(item + " not available");
        }
    }

    @Override
    public void insertCoins(List<Coin> coins) {
        for (Coin coin : coins) {
            currentBalance += coin.getDenomination();
            coinInventory.add(coin);
        }
    }

    @Override
    public Bucket<Item, List<Coin>> collectItemAndChange() {
        Item item = collectItem();
        totalSales = totalSales + currentItem.getPrice();

        List<Coin> change = collectChange();

        return new Bucket<Item, List<Coin>>(item, change);
    }

    @Override
    public List<Coin> refund(Item item) {
        return null;
    }

    @Override
    public void reset() {
        itemInventory.clear();
        coinInventory.clear();
        totalSales = 0;
        currentItem = null;
        currentBalance = 0;
    }

    public void printStats() {
        System.out.println("Total Sales : " + totalSales);
        System.out.println("Current Item Inventory : " + itemInventory);
        System.out.println("Current Cash Inventory : " + coinInventory);
    }

    private Item collectItem() throws InsufficientChangeException, ItemNotAvailableException {
        if (isFullPaid()) {
            if (hasSufficientChange()) {
                itemInventory.deduct(currentItem);
                return currentItem;
            }
            throw new InsufficientChangeException("Not Sufficient change in Inventory");

        }

        long remainingBalance = currentItem.getPrice() - currentBalance;
        throw new NotFullPaidException("Price not full paid, remaining : ", remainingBalance);
    }

    private void updateCashInventory(List<Coin> change) {
        for (Coin c : change) {
            coinInventory.deduct(c);
        }
    }

    private List<Coin> collectChange() {
        long changeAmount = currentBalance - currentItem.getPrice();
        List<Coin> change = getChange(changeAmount);
        updateCashInventory(change);
        currentBalance = 0;
        currentItem = null;
        return change;
    }

    private boolean isFullPaid() {
        return currentBalance >= currentItem.getPrice();
    }

    private boolean hasSufficientChange() {
        return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
    }

    private boolean hasSufficientChangeForAmount(long amount) {
        boolean hasChange = true;
        try {
            getChange(amount);
        } catch (InsufficientChangeException e) {
            hasChange = false;
        }

        return hasChange;
    }

    private List<Coin> getChange(long amount) throws InsufficientChangeException {
        List<Coin> changes = Collections.EMPTY_LIST;

        if (amount > 0) {
            changes = new ArrayList<Coin>();
            long balance = amount;
            while (balance > 0) {
                if (balance >= Coin.RUPEE_1.getDenomination() && coinInventory.isAvailable(Coin.RUPEE_1)) {
                    changes.add(Coin.RUPEE_1);
                    balance = balance - Coin.RUPEE_1.getDenomination();
                } else if (balance >= Coin.RUPEE_2.getDenomination() && coinInventory.isAvailable(Coin.RUPEE_2)) {
                    changes.add(Coin.RUPEE_2);
                    balance = balance - Coin.RUPEE_2.getDenomination();
                } else if (balance >= Coin.RUPEE_5.getDenomination() && coinInventory.isAvailable(Coin.RUPEE_5)) {
                    changes.add(Coin.RUPEE_5);
                    balance = balance - Coin.RUPEE_5.getDenomination();
                } else if (balance >= Coin.RUPEE_10.getDenomination() && coinInventory.isAvailable(Coin.RUPEE_10)) {
                    changes.add(Coin.RUPEE_10);
                    balance = balance - Coin.RUPEE_10.getDenomination();
                } else {
                    throw new InsufficientChangeException("NotSufficientChange, please try another product");
                }
            }
        }

        return changes;
    }
}
