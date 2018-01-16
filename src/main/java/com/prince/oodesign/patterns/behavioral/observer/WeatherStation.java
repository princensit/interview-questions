package com.prince.oodesign.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Prince Raj
 */
public class WeatherStation implements Subject {

    private Temperature temperature;

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature);
        }
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        System.out.println("New temperature recorded: " + temperature.getTemperature());
        this.temperature = temperature;
        this.notifyObservers();
    }
}
