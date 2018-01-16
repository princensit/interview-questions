package com.prince.oodesign.patterns.behavioral.observer;

/**
 * @author Prince Raj
 */
public class Fan implements Observer {

    private final Subject subject;

    public Fan(Subject weatherStation) {
        this.subject = weatherStation;
        weatherStation.registerObserver(this);
    }

    @Override
    public void update(Temperature temperature) {
        if (temperature.getTemperature() > 25) {
            System.out.println("Turning on the fan");
        } else {
            System.out.println("Turning off the fan");
        }
    }
}
