package com.prince.oodesign.patterns.structural.marker;

/**
 * Marker Interface in java is an interface with no fields or methods within it.
 *
 * This design pattern used with languages that provide run-time type information about objects. It provides a means to
 * associate metadata with a class where the language does not have explicit support for such metadata.
 *
 * A major problem with marker interfaces is that an interface defines a contract for implementing classes, and that
 * contract is inherited by all subclasses. This means that you cannot "unimplement" a marker. In the example given, if
 * you create a subclass that you do not want to serialize (perhaps because it depends on transient state), you must
 * resort to explicitly throwing NotSerializableException (per ObjectOutputStream docs)
 *
 * Ex - {@link java.io.Serializable}, {@link Cloneable}, {@link java.rmi.Remote},
 * {@link javax.annotation.concurrent.ThreadSafe}
 *
 * @author Prince Raj
 */
public class Main {

}
