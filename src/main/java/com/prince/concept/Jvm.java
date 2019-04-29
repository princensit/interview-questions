package com.prince.concept;

/**
 * JVM is a specification where working of JVM is specified. But implementation provider is
 * independent to choose the algorithm. Its implementation has been provided by Sun and other
 * companies.
 *
 * A Java virtual machine's main job is to load class files and execute the bytecodes they
 * contain.JVM contains a class loader, which loads class files from both the program and the Java
 * API. Only those class files from the Java API that are actually needed by a running program are
 * loaded into the virtual machine. The bytecodes are executed in an execution engine.
 *
 * The simplest kind of execution engine just interprets the bytecodes one at a time. Another kind
 * of execution engine, one that is faster but requires more memory, is a just-in-time compiler. In
 * this scheme, the bytecodes of a method are compiled to native machine code the first time the
 * method is invoked. The native machine code for the method is then cached, so it can be re-used
 * the next time that same method is invoked.
 *
 * <pre>
 * The Class Loader Architecture:
 * A class loader is an object that is responsible for loading
 * classes. In a JVM, each and every class is loaded by some instance of a java.lang.ClassLoader.
 * The class ClassLoader is an abstract class.
 *
 * 1. Bootstrap ClassLoader (mostly written in C) - JRE/lib/rt.jar
 * 2. Extension ClassLoader - JRE/lib/ext or any directory denoted by java.ext.dirs
 * 3. Application ClassLoader - CLASSPATH environment variable, -classpath or -cp option, Class-Path attribute of Manifest inside JAR file
 * </pre>
 *
 * A class is loaded only when needed, and this is done in above order of class loader.
 *
 * <pre>
 * Execution Engine:
 * Execution Engine executes the bytecode that is assigned to the runtime data
 * areas in the JVM through class loader. The bytecode can be changed to the suitable language in
 * one of two ways.
 * 1. Interpreter:
 * It is used to run Java applications from the command line. It takes
 * as an argument the name of a class file to run. It interprets and executes instructions one by
 * one.
 * 2. Just in time (JIT) compiler:
 * The JIT compiler has been introduced to compensate for the disadvantages of the interpreter.
 * The execution engine runs as an interpreter first, and at the appropriate time, the JIT compiler compiles the entire
 * bytecode to change it to native code. After that, the execution engine no longer interprets the method, but directly
 * executes using native code. Execution in native code is much faster than interpreting instructions one by one. The
 * compiled code can be executed quickly since the native code is stored in the cache.
 * </pre>
 *
 * Oracle Hotspot VM uses a JIT compiler called Hotspot Compiler. It is called Hotspot because
 * Hotspot Compiler searches the 'Hotspot' that requires compiling with the highest priority through
 * profiling, and then it compiles the hotspot to native code. If the method that has the bytecode
 * compiled is no longer frequently invoked, in other words, if the method is not the hotspot any
 * more, the Hotspot VM removes the native code from the cache and runs in interpreter mode.
 *
 * <pre>
 * For running java programs, JRE is sufficient. JRE is targeted for execution of Java files.
 * JVM = JIT compiler
 * JRE = JVM + Java Packages (like util, math, lang etc) + runtime libraries (rt.jar)
 * JDK = JRE + development tools (javac, jar, javap, etc.)
 * </pre>
 *
 * @author Prince Raj
 */
public class Jvm {
}
