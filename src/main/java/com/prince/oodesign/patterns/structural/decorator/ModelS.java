package com.prince.oodesign.patterns.structural.decorator;

/**
 * @author Prince Raj
 */
public class ModelS extends Car {

    @Override
    public String getDescription() {
        return "Model S";
    }

    @Override
    public int getCost() {
        return 33000;
    }
}
