package com.prince.design.leader_election;

/**
 * @author Prince Raj
 */
public class DeadNodeException extends RuntimeException {

    public DeadNodeException() {
        super();
    }

    public DeadNodeException(String message) {
        super(message);
    }

    public DeadNodeException(Throwable cause) {
        super(cause);
    }

    public DeadNodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
