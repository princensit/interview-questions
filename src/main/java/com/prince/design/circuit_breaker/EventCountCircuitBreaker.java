package com.prince.design.circuit_breaker;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * A simple implementation of the <a href="http://martinfowler.com/bliki/CircuitBreaker.html">Circuit Breaker</a>
 * pattern that counts specific events.
 * </p>
 * <p>
 * A <em>circuit breaker</em> can be used to protect an application against unreliable services or unexpected load. A
 * newly created {@code EventCountCircuitBreaker} object is initially in state <em>closed</em> meaning that no problem
 * has been detected. When the application encounters specific events (like errors or service timeouts), it tells the
 * circuit breaker to increment an internal counter. If the number of events reported in a specific time interval
 * exceeds a configurable threshold, the circuit breaker changes into state <em>open</em>. This means that there is a
 * problem with the associated sub system; the application should no longer call it, but give it some time to settle
 * down. The circuit breaker can be configured to switch back to <em>closed</em> state after a certain time frame if the
 * number of events received goes below a threshold.
 * </p>
 * <p>
 * When a {@code EventCountCircuitBreaker} object is constructed the following parameters can be provided:
 * </p>
 * <ul>
 * <li>A threshold for the number of events that causes a state transition to <em>open</em> state. If more events are
 * received in the configured check interval, the circuit breaker switches to <em>open</em> state.</li>
 * <li>The interval for checks whether the circuit breaker should open. So it is possible to specify something like "The
 * circuit breaker should open if more than 10 errors are encountered in a minute."</li>
 * <li>The same parameters can be specified for automatically closing the circuit breaker again, as in "If the number of
 * requests goes down to 100 per minute, the circuit breaker should close itself again". Depending on the use case, it
 * may make sense to use a slightly lower threshold for closing the circuit breaker than for opening it to avoid
 * continuously flipping when the number of events received is close to the threshold.</li>
 * </ul>
 * <p>
 * This class supports the following typical use cases:
 * </p>
 * <p>
 * <strong>Protecting against load peaks</strong>
 * </p>
 * <p>
 * Imagine you have a server which can handle a certain number of requests per minute. Suddenly, the number of requests
 * increases significantly - maybe because a connected partner system is going mad or due to a denial of service attack.
 * A {@code EventCountCircuitBreaker} can be configured to stop the application from processing requests when a sudden
 * peak load is detected and to start request processing again when things calm down. The following code fragment shows
 * a typical example of such a scenario. Here the {@code EventCountCircuitBreaker} allows up to 1000 requests per minute
 * before it interferes. When the load goes down again to 800 requests per second it switches back to state
 * <em>closed</em>:
 * </p>
 *
 * <pre>
 * EventCountCircuitBreaker breaker = new EventCountCircuitBreaker(1000, 1, TimeUnit.MINUTE, 800);
 * ...
 * public void handleRequest(Request request) {
 *     if (breaker.incrementAndCheckState()) {
 *         // actually handle this request
 *     } else {
 *         // do something else, e.g. send an error code
 *     }
 * }
 * </pre>
 * <p>
 * <strong>Deal with an unreliable service</strong>
 * </p>
 * <p>
 * In this scenario, an application uses an external service which may fail from time to time. If there are too many
 * errors, the service is considered down and should not be called for a while. This can be achieved using the following
 * pattern - in this concrete example we accept up to 5 errors in 2 minutes; if this limit is reached, the service is
 * given a rest time of 10 minutes:
 * </p>
 *
 * <pre>
 * EventCountCircuitBreaker breaker = new EventCountCircuitBreaker(5, 2, TimeUnit.MINUTE, 5, 10, TimeUnit.MINUTE);
 * ...
 * public void handleRequest(Request request) {
 *     if (breaker.checkState()) {
 *         try {
 *             service.doSomething();
 *         } catch (ServiceException ex) {
 *             breaker.incrementAndCheckState();
 *         }
 *     } else {
 *         // return an error code, use an alternative service, etc.
 *     }
 * }
 * </pre>
 * <p>
 * In addition to automatic state transitions, the state of a circuit breaker can be changed manually using the methods
 * {@link #open()} and {@link #close()}. It is also possible to register {@code PropertyChangeListener} objects that get
 * notified whenever a state transition occurs. This is useful, for instance to directly react on a freshly detected
 * error condition.
 * </p>
 * <p>
 * <em>Implementation notes:</em>
 * </p>
 * <ul>
 * <li>This implementation uses non-blocking algorithms to update the internal counter and state. This should be pretty
 * efficient if there is not too much contention.</li>
 * <li>This implementation is not intended to operate as a high-precision timer in very short check intervals. It is
 * deliberately kept simple to avoid complex and time-consuming state checks. It should work well in time intervals from
 * a few seconds up to minutes and longer. If the intervals become too short, there might be race conditions causing
 * spurious state transitions.</li>
 * <li>The handling of check intervals is a bit simplistic. Therefore, there is no guarantee that the circuit breaker is
 * triggered at a specific point in time; there may be some delay (less than a check interval).</li>
 * </ul>
 *
 * @author Prince Raj
 */
public class EventCountCircuitBreaker extends AbstractCircuitBreaker<Integer> {

    // strategy map for each state
    private static final Map<State, StateStrategy> STATE_STRATEGY_MAP = createStrategyMap();;

    // stores information about the current check interval
    private final AtomicReference<CheckIntervalData> checkIntervalData;

    // threshold for opening the circuit breaker
    private final int openingThreshold;

    // time interval for opening the circuit breaker
    private final long openingInterval;

    // threshold for closing the circuit breaker
    private final int closingThreshold;

    // time interval for closing the circuit breaker
    private final long closingInterval;

    public EventCountCircuitBreaker(
            final int openingThreshold,
            final long openingInterval,
            final TimeUnit openingUnit,
            final int closingThreshold,
            final long closingInterval,
            final TimeUnit closingUnit) {
        super();
        this.openingThreshold = openingThreshold;
        this.openingInterval = openingUnit.toNanos(openingInterval);
        this.closingThreshold = closingThreshold;
        this.closingInterval = closingUnit.toNanos(closingInterval);
        this.checkIntervalData = new AtomicReference<>(new CheckIntervalData(0, 0));
    }

    public EventCountCircuitBreaker(
            final int openingThreshold,
            final long checkInterval,
            final TimeUnit checkUnit,
            final int closingThreshold) {
        this(openingThreshold, checkInterval, checkUnit, closingThreshold, checkInterval, checkUnit);
    }

    public EventCountCircuitBreaker(final int threshold, final long checkInterval, final TimeUnit checkUnit) {
        this(threshold, checkInterval, checkUnit, threshold, checkInterval, checkUnit);
    }

    @Override
    public void open() {
        super.open();
        checkIntervalData.set(new CheckIntervalData(0, now()));
    }

    @Override
    public void close() {
        super.close();
        checkIntervalData.set(new CheckIntervalData(0, now()));
    }

    /**
     * {@inheritDoc} This implementation checks the internal event counter against the threshold values and the check
     * intervals. This may cause a state change of this circuit breaker.
     */
    @Override
    public boolean checkState() {
        return performStateCheck(0);
    }

    @Override
    public boolean incrementAndCheckState(Integer increment) throws CircuitBreakingException {
        return performStateCheck(increment);
    }

    /**
     * Increments the monitored value by <strong>1</strong> and performs a check of the current state of this circuit
     * breaker. This method works like {@link #checkState()}, but the monitored value is incremented before the state
     * check is performed.
     *
     * @return <strong>true</strong> if the circuit breaker is now closed; <strong>false</strong> otherwise
     */
    public boolean incrementAndCheckState() {
        return incrementAndCheckState(1);
    }

    public int getOpeningThreshold() {
        return openingThreshold;
    }

    public long getOpeningInterval() {
        return openingInterval;
    }

    public int getClosingThreshold() {
        return closingThreshold;
    }

    public long getClosingInterval() {
        return closingInterval;
    }

    /**
     * Actually checks the state of this circuit breaker and executes a state transition if necessary.
     *
     * @param increment the increment for the internal counter
     * @return a flag whether the circuit breaker is now closed
     */
    private boolean performStateCheck(final int increment) {
        CheckIntervalData currentData;
        CheckIntervalData nextData;
        State currentState;

        do {
            final long time = now();
            currentState = state.get();
            currentData = checkIntervalData.get();
            nextData = nextCheckIntervalData(increment, currentData, currentState, time);
        } while (!updateCheckIntervalData(currentData, nextData));

        // This might cause a race condition if other changes happen in between!
        // Refer to the header comment!
        if (stateStrategy(currentState).isStateTransition(this, currentData, nextData)) {
            currentState = currentState.oppositeState();
            changeStateAndStartNewCheckInterval(currentState);
        }
        return !isOpen(currentState);
    }

    /**
     * Updates the {@code CheckIntervalData} object. The current data object is replaced by the one modified by the last
     * check. The return value indicates whether this was successful. If it is <strong>false</strong>, another thread
     * interfered, and the whole operation has to be redone.
     *
     * @param currentData the current check data object
     * @param nextData the replacing check data object
     * @return a flag whether the update was successful
     */
    private boolean updateCheckIntervalData(final CheckIntervalData currentData, final CheckIntervalData nextData) {
        return currentData == nextData || checkIntervalData.compareAndSet(currentData, nextData);
    }

    /**
     * Calculates the next {@code CheckIntervalData} object based on the current data and the current state. The next
     * data object takes the counter increment and the current time into account.
     *
     * @param increment the increment for the internal counter
     * @param currentData the current check data object
     * @param currentState the current state of the circuit breaker
     * @param time the current time
     * @return the updated {@code CheckIntervalData} object
     */
    private CheckIntervalData nextCheckIntervalData(
            final int increment,
            final CheckIntervalData currentData,
            final State currentState,
            final long time) {
        CheckIntervalData nextData;
        if (stateStrategy(currentState).isCheckIntervalFinished(this, currentData, time)) {
            nextData = new CheckIntervalData(increment, time);
        } else {
            nextData = currentData.increment(increment);
        }
        return nextData;
    }

    /**
     * Changes the state of this circuit breaker and also initializes a new {@code CheckIntervalData} object.
     *
     * @param newState the new state to be set
     */
    private void changeStateAndStartNewCheckInterval(final State newState) {
        changeState(newState);
        checkIntervalData.set(new CheckIntervalData(0, now()));
    }

    private long now() {
        return System.nanoTime();
    }

    /**
     * Returns the {@code StateStrategy} object responsible for the given state.
     *
     * @param state the state
     * @return the corresponding {@code StateStrategy}
     * @throws CircuitBreakingException if the strategy cannot be resolved
     */
    private static StateStrategy stateStrategy(final State state) {
        return STATE_STRATEGY_MAP.get(state);
    }

    private static Map<State, StateStrategy> createStrategyMap() {
        final Map<State, StateStrategy> map = new EnumMap<>(State.class);
        map.put(State.CLOSED, new StateStrategyClosed());
        map.put(State.OPEN, new StateStrategyOpen());
        return map;
    }
}
