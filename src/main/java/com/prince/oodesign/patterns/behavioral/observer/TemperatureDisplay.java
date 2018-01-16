package com.prince.oodesign.patterns.behavioral.observer;

/**
 * @author Prince Raj
 */
public class TemperatureDisplay implements Observer {

    private final Subject subject;

    public TemperatureDisplay(Subject weatherStation) {
        this.subject = weatherStation;
        weatherStation.registerObserver(this);
    }

    @Override
    public void update(Temperature temperature) {
        System.out.println("Displaying temperature: " + temperature.getTemperature());
    }
}
