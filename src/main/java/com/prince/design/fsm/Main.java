package com.prince.design.fsm;

/**
 * Testing FSM implementation
 *
 * <pre>
 * A ----E1----> B ----auto----> C ----E2----> E ----E3----> D
 *                                 ------------E4----------> D
 * </pre>
 *
 * @author Prince Raj
 */
public class Main {

    public static void main(String[] args) {
        FSM fsm = new FSMImpl("fsm");
        // / fsm.setDebug(true);

        // state A
        String state = "A";
        Runnable aEntryCode = getRunnable(state + " - entry code");
        Runnable aAlwaysRunCode = getRunnable(state + " - always run code");
        Runnable aExitCode = getRunnable(state + " - exit code");
        fsm.addState(state, aEntryCode, aAlwaysRunCode, aExitCode);

        // state B
        state = "B";
        aEntryCode = getRunnable(state + " - entry code");
        aAlwaysRunCode = getRunnable(state + " - always run code");
        aExitCode = getRunnable(state + " - exit code");
        fsm.addState(state, aEntryCode, aAlwaysRunCode, aExitCode);

        // state C
        state = "C";
        fsm.addState(state);

        // state D
        state = "D";
        aEntryCode = getRunnable(state + " - entry code");
        aAlwaysRunCode = getRunnable(state + " - always run code");
        aExitCode = getRunnable(state + " - exit code");
        fsm.addState(state, aEntryCode, aAlwaysRunCode, aExitCode);

        // state E
        state = "E";
        aEntryCode = getRunnable(state + " - entry code");
        aAlwaysRunCode = getRunnable(state + " - always run code");
        aExitCode = getRunnable(state + " - exit code");
        fsm.addState(state, aEntryCode, aAlwaysRunCode, aExitCode);

        Transition trans1 = new Transition("E1", "A", "B");
        Transition trans2 = new Transition("E2", "C", "E");
        Transition trans3 = new Transition("E3", "E", "D");
        Transition trans4 = new Transition("E4", "C", "D");

        fsm.setAutoTransition("B", "C");
        fsm.addTransition(trans1);
        fsm.addTransition(trans2);
        fsm.addTransition(trans3);
        fsm.addTransition(trans4);

        fsm.addEvent("E1");
        fsm.addEvent("E4");
        // nothing should happen as this event is not applicable on current state
        fsm.addEvent("E2");
    }

    private static Runnable getRunnable(final String msg) {
        return new Runnable() {

            @Override
            public void run() {
                System.out.println(msg);
            }
        };
    }
}
