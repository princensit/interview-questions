package com.prince.oodesign.patterns.creational.dependency_injection;

/**
 * A class accepts the objects it requires from an injector instead of creating the objects directly.
 *
 * There are at least three ways an object can receive a reference to an external module:
 *
 * 1. constructor injection: the dependencies are provided through a class constructor.
 *
 * 2. setter injection: the client exposes a setter method that the injector uses to inject the dependency.
 *
 * 3. interface injection: the dependency provides an injector method that will inject the dependency into any client
 * passed to it. Clients must implement an interface that exposes a setter method that accepts the dependency.
 *
 * @author Prince Raj
 */
public class Client {

    private Service service;

    // 1. Constructor - This method requires the client to provide a parameter in a constructor for the dependency.
    Client(Service service) {
        // Save the reference to the passed-in service inside this client
        this.service = service;
    }

    // 2. Setter method - This method requires the client to provide a setter method for the dependency.
    public void setService(Service service) {
        // Save the reference to the passed-in service inside this client
        this.service = service;
    }
}

// 3. Interface injection - This is simply the client publishing a role interface to the setter methods of the client's
// dependencies. It can be used to establish how the injector should talk to the client when injecting dependencies.
// Service setter interface.
interface ServiceSetter {

    void setService(Service service);
}

// Client class
class Client2 implements ServiceSetter {

    // Internal reference to the service used by this client.
    private Service service;

    // Set the service that this client is to use.
    @Override
    public void setService(Service service) {
        this.service = service;
    }
}