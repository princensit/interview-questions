package com.prince.oodesign.patterns.structural.decorator;

/**
 * @author Prince Raj
 */
public class ModelX extends Car {

    @Override
    public String getDescription() {
        return "Model X";
    }

    @Override
    public int getCost() {
        return 50000;
    }
}
