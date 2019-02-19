package com.prince.concept;

/**
 * <pre>
 *
 * Java has two types of exceptions: checked exceptions and unchecked exceptions. Unchecked exceptions do not need to be
 * declared in a method or a constructor’s throws clause, if they can be thrown by the execution of the method or the
 * constructor, and propagate outside the method or constructor boundary. On the other hand, checked exceptions must be
 * declared in a method or a constructor’s throws clause
 *
 * Exception and Error classes are both subclasses of the Throwable class. The Exception class is used for exceptional
 * conditions that a user’s program should catch. The Error class should be handled by JVM.
 *
 * The Exception object will be garbage collected in the next garbage collection, once exception handling done.
 *
 * A finally block will be executed whether or not an exception is thrown and is used to release those resources held by
 * the application. finalize() is a protected method of the Object class, which is called by the JVM just before an object
 * is garbage collected.
 *
 * ClassNotFoundException and NoClassDefFoundError occur when a particular class is not found at runtime.
 * ClassNotFoundException is an exception that occurs when you try to load a class at run time using Class.forName() or loadClass()
 * methods and mentioned classes are not found in the classpath.
 * NoClassDefFoundError is an error that occurs when a particular class is present at compile time, but was missing at run time.
 *
 * </pre>
 *
 * @author Prince Raj
 */
public class Exceptions {

}
