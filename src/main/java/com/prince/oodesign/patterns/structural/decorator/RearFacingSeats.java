package com.prince.oodesign.patterns.structural.decorator;

/**
 * @author Prince Raj
 */
public class RearFacingSeats extends CarOptions {

    private final Car decoratedCar;

    public RearFacingSeats(Car car) {
        super();
        this.decoratedCar = car;
    }

    @Override
    public String getDescription() {
        return decoratedCar.getDescription() + ", Rear facing seats";
    }

    @Override
    public int getCost() {
        return decoratedCar.getCost() + 2000;
    }
}
