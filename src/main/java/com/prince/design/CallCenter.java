package com.prince.design;

import java.util.List;

/**
 * @author Prince Raj
 */
public class CallCenter {

    public static void main(String[] args) {
        CallHandler callHandler = CallHandler.getInstance();

        Caller caller = new Caller();
        caller.setName("Hello");
        caller.setMinimumRank(Rank.MANAGER);

        callHandler.dispatchCall(caller);
    }
}

// CallHandler is implemented as a singleton class. It represents the body of the program,
// and all calls are funneled first through it.
class CallHandler {

    private static CallHandler instance;

    // 3 levels of employees: respondents, managers, directors
    private static final int LEVELS = 3;

    // initialize 10 respondents, 4 managers and 2 directors
    private static final int RESPONDENTS_COUNT = 10;

    private static final int MANAGERS_COUNT = 4;

    private static final int DIRECTORS_COUNT = 2;

    // list of employees by level
    // employeeLevels[0] = respondents
    // employeeLevels[1] = managers
    // employeeLevels[2] = directors
    private List<List<Employee>> employeeLevels;

    // queues for each call's rank
    List<List<Call>> callQueues;

    public static CallHandler getInstance() {
        if (instance == null) {
            synchronized (CallHandler.class) {
                if (instance == null) {
                    instance = new CallHandler();
                }
            }
        }

        return instance;
    }

    // routes the call to available employee or put in the queue if no employee available
    public void dispatchCall(Caller caller) {
        Call call = new Call(caller);
        dispatchCall(call);
    }

    public void dispatchCall(Call call) {
        Employee employee = getHandlerForCall(call);
        if (employee != null) {
            employee.receiveCall(call);
            call.setHandler(employee);
        } else {
            call.reply("Please wait for free employee to reply.");
            callQueues.get(call.getRank().getValue()).add(call);
        }
    }

    // gets the first available employee who can handle this call
    public Employee getHandlerForCall(Call call) {
        // TODO Implement me!

        return null;
    }

    // An employee got free. Look for waiting call, assign true if call is assigned, otherwise false.
    public boolean assignCall(Employee employee) {
        // TODO Implement me!

        return true;
    }
}

class Call {

    // minimum rank of the employee who can handle this call
    private Rank rank;

    // person who is calling
    private Caller caller;

    // employee who is handling call
    private Employee handler;

    public Call(Caller caller) {
        this.rank = Rank.RESPONDENT;
        this.caller = caller;
    }

    public void setHandler(Employee handler) {
        this.handler = handler;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Rank incrementRank() {
        // TODO Implement me!

        return null;
    }

    public void disconnect() {
        // TODO Implement me!
    }

    public void reply(String message) {
        // TODO Implement me!
    }
}

abstract class Employee {

    protected Rank rank;

    private Call currentCall = null;

    // start the conversation
    public void receiveCall(Call call) {
        // TODO Implement me!

        currentCall = call;
    }

    // the issue is resolved, finish the call
    public void callCompleted() {
        // TODO Implement me!

        currentCall = null;
    }

    // The issue has not been resolved, escalating the call to another employee
    public void escalateAndReassign() {
        // TODO Implement me!

        currentCall = null;
    }

    public boolean assignNewCall() {
        // TODO Implement me!

        return true;
    }

    public boolean isFree() {
        return currentCall == null;
    }

    public Rank getRank() {
        return rank;
    }
}

class Respondent extends Employee {

    public Respondent() {
        rank = Rank.RESPONDENT;
    }
}

class Manager extends Employee {

    public Manager() {
        rank = Rank.MANAGER;
    }
}

class Director extends Employee {

    public Director() {
        rank = Rank.DIRECTOR;
    }
}

class Caller {

    private String name;

    // minimum rank required for this caller
    private Rank minimumRank;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rank getMinimumRank() {
        return minimumRank;
    }

    public void setMinimumRank(Rank minimumRank) {
        this.minimumRank = minimumRank;
    }
}

enum Rank {
    RESPONDENT(0), MANAGER(1), DIRECTOR(2);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}