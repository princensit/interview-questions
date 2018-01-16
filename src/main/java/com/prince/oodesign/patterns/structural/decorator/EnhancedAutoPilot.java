package com.prince.oodesign.patterns.structural.decorator;

/**
 * @author Prince Raj
 */
public class EnhancedAutoPilot extends CarOptions {

    private final Car decoratedCar;

    public EnhancedAutoPilot(Car car) {
        super();
        this.decoratedCar = car;
    }

    @Override
    public String getDescription() {
        return decoratedCar.getDescription() + ", Enhance AutoPilot";
    }

    @Override
    public int getCost() {
        return decoratedCar.getCost() + 5000;
    }
}
