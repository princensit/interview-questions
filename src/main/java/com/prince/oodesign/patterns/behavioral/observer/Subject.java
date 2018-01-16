package com.prince.oodesign.patterns.behavioral.observer;

/**
 * @author Prince Raj
 */
public interface Subject {

    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
