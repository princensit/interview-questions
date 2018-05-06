package com.prince.design.circuit_breaker;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Prince Raj
 */
public abstract class AbstractCircuitBreaker<T> implements CircuitBreaker<T> {

    /**
     * The name of the <em>open</em> property as it is passed to registered change listeners.
     */
    private static final String PROPERTY_NAME = "open";

    /** The current state of this circuit breaker. */
    protected final AtomicReference<State> state = new AtomicReference<>(State.CLOSED);

    // An object for managing change listeners registered at this instance.
    private final PropertyChangeSupport changeSupport;

    AbstractCircuitBreaker() {
        this.changeSupport = new PropertyChangeSupport(this);
    }

    @Override
    public boolean isOpen() {
        return isOpen(state.get());
    }

    @Override
    public boolean isClosed() {
        return !isOpen();
    }

    @Override
    public void open() {
        changeState(State.OPEN);
    }

    @Override
    public void close() {
        changeState(State.CLOSED);
    }

    @Override
    public abstract boolean checkState();

    @Override
    public abstract boolean incrementAndCheckState(T increment);

    /**
     * Adds a change listener to this circuit breaker. This listener is notified whenever the state of this circuit
     * breaker changes. If the listener is <strong>null</strong>, it is silently ignored.
     *
     * @param listener the listener to be added
     */
    public void addChangeListener(final PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Removes the specified change listener from this circuit breaker.
     *
     * @param listener the listener to be removed
     */
    public void removeChangeListener(final PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    protected void changeState(State newState) {
        if (state.compareAndSet(newState.oppositeState(), newState)) {
            changeSupport.firePropertyChange(PROPERTY_NAME, !isOpen(newState), isOpen(newState));
        }
    }

    protected boolean isOpen(final State state) {
        return state == State.OPEN;
    }
}
