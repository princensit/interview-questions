package com.prince.feature;

/**
 * <pre>
 * 1. Parallel Full GC for G1
 * 2. Local Variable Type Inference using var keyword
 * 3. Optional*.orElseThrow() throws NoSuchElementException if no value is present
 * 4. JVMs are now aware of being run in a Docker container and will extract container-specific configuration instead
 *    of querying the operating system itself – it applies to data like the number of CPUs and total memory that have
 *    been allocated to the container.
 *    Many flags added like -XX:-UseContainerSupport, -XX:ActiveProcessorCount=count, -XX:InitialRAMPercentage,
 *    -XX:MaxRAMPercentage, -XX:MinRAMPercentage
 * 5. Root Certificates: The cacerts keystore, which was initially empty so far, is intended to contain a set of root
 *    certificates that can be used to establish trust in the certificate chains used by various security protocols.
 *
 *    As a result, critical security components such as TLS didn't work by default under OpenJDK builds.
 *    With Java 10, Oracle has open-sourced the root certificates in Oracle’s Java SE Root CA program in order to make
 *    OpenJDK builds more attractive to developers and to reduce the differences between those builds and Oracle JDK builds.
 * 6. Time-Based Release Versioning: A new Java release every six months.
 * 7. Deprecations and Removals
 * 8. New Java Just-In-Time (JIT) compiler, called Graal, is much more sophisticated than the javac compiler, and it
 *    runs complex optimizations to generate high-quality machine code.
 * </pre>
 *
 * @author Prince Raj
 */
public class Java10Features {
}
