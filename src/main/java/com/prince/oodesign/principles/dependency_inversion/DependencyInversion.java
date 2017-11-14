package com.prince.oodesign.principles.dependency_inversion;

/**
 * Dependency Inversion Principle states that we should decouple high level modules from low level modules, introducing
 * an abstraction layer between the high level classes and low level classes. Further more it inverts the dependency:
 * instead of writing our abstractions based on details, then we should write the details based on abstractions.
 *
 * According to this principle the way of designing a class structure is to start from high level modules to the low
 * level modules:
 *
 * High Level Classes --> Abstraction Layer --> Low Level Classes
 *
 * In this case instantiation of new low level objects inside the high level classes(if necessary) can not be done using
 * the operator new. Instead, some of the Creational design patterns can be used, such as Factory Method, Abstract
 * Factory, Prototype.
 *
 * Ex - template pattern
 *
 * @author Prince Raj
 */
public class DependencyInversion {

}
