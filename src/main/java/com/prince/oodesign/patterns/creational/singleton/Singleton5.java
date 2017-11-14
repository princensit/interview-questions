package com.prince.oodesign.patterns.creational.singleton;

import java.io.File;

/**
 * @author Prince Raj
 */

public class Singleton5 {

    public static void main(String[] args) {
        Singleton.INSTANCE.doSomething();
    }

    enum Singleton {
        INSTANCE(new File("."));

        private final File file;

        Singleton(File file) {
            this.file = file;
        }

        public void doSomething() {
            System.out.println("hello");
        }
    }
}
