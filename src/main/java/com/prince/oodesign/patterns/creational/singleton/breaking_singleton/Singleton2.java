package com.prince.oodesign.patterns.creational.singleton.breaking_singleton;

import java.io.File;

/**
 * @author Prince Raj
 */
public enum Singleton2 {

    INSTANCE(new File("."));

    private final File file;

    Singleton2(File file) {
        this.file = file;
    }

    public void doSomething() {
        System.out.println("hello");
    }
}
