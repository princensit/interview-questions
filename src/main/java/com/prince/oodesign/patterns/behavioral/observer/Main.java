package com.prince.oodesign.patterns.behavioral.observer;

/**
 * This is a design pattern in which an object, called the subject, maintains a list of its dependents, called
 * observers, and notifies them automatically of any state changes, usually by calling one of their methods.
 *
 * It is mainly used to implement distributed event handling systems, in "event driven" software.
 *
 * The observer pattern is also a key part in the familiar model–view–controller (MVC) architectural pattern.
 *
 * The observer pattern can cause memory leaks, because in basic implementation it requires both explicit registration
 * and explicit deregistration. This can be prevented by the subject holding weak references to the observers.
 *
 * This type of implementation is considered "tightly coupled", forcing both the observers and the subject to be aware
 * of each other and have access to their internal parts, creating possible issues of scalability, speed, message
 * recovery and maintenance (also called event or notification loss), the lack of flexibility in conditional dispersion
 * and possible hindrance to desired security measures.
 *
 * In some (non-polling) implementations of the publish-subscribe pattern (also called the pub-sub pattern), this is
 * solved by creating a dedicated "message queue" server and at times an extra "message handler" object, as added stages
 * between the observer and the observed object whose state is being checked, thus "decoupling" the software components.
 *
 * @author Prince Raj
 */
public class Main {

    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        // create observers
        TemperatureDisplay o1 = new TemperatureDisplay(weatherStation);
        Fan o2 = new Fan(weatherStation);

        Temperature temp = new Temperature();
        temp.setTemperature(23);
        weatherStation.setTemperature(temp);

        System.out.println();

        temp = new Temperature();
        temp.setTemperature(27);
        weatherStation.setTemperature(temp);
    }
}
