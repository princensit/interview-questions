package com.prince.design.connection_pool;

/**
 * @author Prince Raj
 */
public interface ConnectionOperations {

    void execute(String message);

    void printStats();
}
