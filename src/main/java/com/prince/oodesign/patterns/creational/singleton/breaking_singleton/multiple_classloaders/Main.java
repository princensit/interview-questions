package com.prince.oodesign.patterns.creational.singleton.breaking_singleton.multiple_classloaders;

/**
 * Singleton pattern can be broken by using multiple class loaders. When the same class is loaded by two different class
 * loaders, that same class is treated as if they are two different classes. That is because the Java identifies unique
 * classes not only using it’s fully qualified name, but also with the class loader which loaded the class. If our
 * singleton above is loaded by two class loaders, there will be two instances of it.
 *
 * @author Prince Raj
 */
public class Main {

}
