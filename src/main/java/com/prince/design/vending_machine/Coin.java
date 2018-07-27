package com.prince.design.vending_machine;

import lombok.Getter;

/**
 * @author Prince Raj
 */
public enum Coin {

    RUPEE_1(1), RUPEE_2(2), RUPEE_5(5), RUPEE_10(10);

    @Getter
    private final int denomination;

    Coin(int denomination) {
        this.denomination = denomination;
    }
}
