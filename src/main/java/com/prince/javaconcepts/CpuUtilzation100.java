package com.prince.javaconcepts;

/**
 * http://code.nomad-labs.com/2010/11/18/identifying-which-java-thread-is-consuming-most-cpu/
 * http://karunsubramanian.com/java/4-things-you-need-to-know-about-cpu-utilization-of-your-java-application/
 *
 * @author Prince Raj
 */
public class CpuUtilzation100 {

    /**
     * <pre>
     * Top reasons for higher CPU utilization:
     * 1. Excessive GC cycles going on
     * 2. Too many application threads active -> more context switching -> hence more CPU cycles
     * 3. Infinite loops or excessive backend calls
     * 4. The app is indeed working hard but host doesn't have enough CPU
     *
     * Steps to identify thread causing excessive CPU usage:
     * 1. top -H (threads toggle view) or simply top with Ctrl+H
     * 2. Get PID of thread with highest CPU
     * 3. Convert PID to HEX
     * 4. Get thread stack dump using jstack command (jstack -l <pid>)
     * 5. In thread stack dump, look for that matching HEX PID
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
