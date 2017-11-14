package com.prince.multithreading;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Prince Raj
 */
public class AtomicStampedReferenceUsage {

    public static void main(String[] args) {
        String initialRef = "initial value referenced";
        int initialStamp = 0;

        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>(initialRef, initialStamp);

        String newRef = "new value referenced";
        int newStamp = initialStamp + 1;

        boolean exchanged = atomicStampedReference.compareAndSet(initialRef, newRef, initialStamp, newStamp);
        System.out.println("exchanged: " + exchanged); // true

        exchanged = atomicStampedReference.compareAndSet(initialRef, "new string", newStamp, newStamp + 1);
        System.out.println("exchanged: " + exchanged); // false

        exchanged = atomicStampedReference.compareAndSet(newRef, "new string", initialStamp, newStamp + 1);
        System.out.println("exchanged: " + exchanged); // false

        exchanged = atomicStampedReference.compareAndSet(newRef, "new string", newStamp, newStamp + 1);
        System.out.println("exchanged: " + exchanged); // true
    }

    private static void detectABA() {
        String initialRef = "initial value referenced";
        int initialStamp = 0;

        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>(initialRef, initialStamp);

        int[] stampHolder = new int[1];
        Object ref = atomicStampedReference.get(stampHolder);

        if (ref == null) {
            // prepare optimistic modification
        }

        // if another thread changes the reference and stamp here,
        // it can be detected

        int[] stampHolder2 = new int[1];
        Object ref2 = atomicStampedReference.get(stampHolder);

        if (ref == ref2 && stampHolder[0] == stampHolder2[0]) {
            // no modification since optimistic modification was prepared

        } else {
            // retry from scratch.
        }
    }
}
