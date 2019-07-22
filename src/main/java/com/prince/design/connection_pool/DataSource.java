package com.prince.design.connection_pool;

/**
 * @author Prince Raj
 */
public class DataSource {

    private String hostname;

    private String port;

    private int initialPoolSize;

    private int maxPoolSize;

    private int incrementStepPoolSize;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getInitialPoolSize() {
        return initialPoolSize;
    }

    public void setInitialPoolSize(int initialPoolSize) {
        this.initialPoolSize = initialPoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getIncrementStepPoolSize() {
        return incrementStepPoolSize;
    }

    public void setIncrementStepPoolSize(int incrementStepPoolSize) {
        this.incrementStepPoolSize = incrementStepPoolSize;
    }
}
