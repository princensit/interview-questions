package com.prince.multithreading;

/**
 * http://tutorials.jenkov.com/java-concurrency/amdahls-law.html
 *
 * @author Prince Raj
 */
public class AmdahlsLaw {

    public static void main(String[] args) {
        System.out.println("T = Total time of serial execution\n"
                + "B = Total time of non-parallizable part\n"
                + "T - B = Total time of parallizable part (when executed serially, not in parallel)");
        System.out.println("The fastest the parallelizable part can be executed with N threads/CPUs is thus:");
        System.out.println("(T - B) / N");
        System.out.println("The total execution time of the program");
        System.out.println("T(N) = B + (T - B) / N");
        System.out.println("T(N) = B + ( T(1) - B ) / N");
        System.out.println("T(5) = 0.4 + ( 1 - 0.4 ) / 5 = 0.52");
        System.out
                .println("Optimizing the Sequential part. If the non-parallelizable part B is optimized by a factor of O");
        System.out.println("T(O,N) = B / O + (1 - B / O) / N");
        System.out.println("Speedup = T / T(O,N)");
        System.out.println("Speedup = 1 / ( B / O + (1 - B / O) / N )");
        System.out.println("With B = 0.4, O = 2 and N = 5");
        System.out.println("Speedup = 1 / ( 0.4 / 2 + (1 - 0.4 / 2) / 5) = 2.777...");
    }
}
