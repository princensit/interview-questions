package com.prince.design.vending_machine;

import java.util.List;

/**
 * @author Prince Raj
 */
public interface VendingMachine {

    int selectItemAndGetPrice(Item item);

    void insertCoins(List<Coin> coins);

    Bucket<Item, List<Coin>> collectItemAndChange();

    List<Coin> refund(Item item);

    void reset();

    void printStats();
}
