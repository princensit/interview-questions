package com.prince.design.vending_machine;

import lombok.Getter;

/**
 * @author Prince Raj
 */
public enum Item {
    COKE("coke", 10), PEPSI("pepsi", 15);

    @Getter
    private final String name;

    @Getter
    private final int price;

    Item(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
