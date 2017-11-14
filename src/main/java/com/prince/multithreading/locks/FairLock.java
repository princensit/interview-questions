package com.prince.multithreading.locks;

import java.util.ArrayList;
import java.util.List;

/**
 * A synchronized block makes no guarantees about the sequence in which threads waiting to enter it are granted
 * access.
 *
 * @author Prince Raj
 */
public class FairLock {

    private boolean locked = false;

    private Thread lockingThread = null;

    private List<QueueObject> waitingThreads = new ArrayList<>();

    public void lock() {

    }

    public synchronized void unlock() {

    }

    private static class QueueObject {

    }
}
