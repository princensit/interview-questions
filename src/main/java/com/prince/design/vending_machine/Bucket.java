package com.prince.design.vending_machine;

import lombok.Data;

/**
 * @author Prince Raj
 */
@Data
public class Bucket<E1, E2> {

    private final E1 first;

    private final E2 second;
}
