package com.prince.design.fsm.predefined;

/**
 * @author Prince Raj
 */
public enum State {
    SUBMITTED(0) {

        @Override
        void preAction() {
            System.out.println("Submitted - pre action");
        }

        @Override
        void postAction() {
            System.out.println("Submitted - post action");
        }
    },
    INITIATED(1) {

        @Override
        void preAction() {
            System.out.println("Initiated - pre action");
        }

        @Override
        void postAction() {
            System.out.println("Initiated - post action");
        }
    },
    FAILED(2) {

        @Override
        void preAction() {
            System.out.println("Failed - pre action");
        }

        @Override
        void postAction() {
            System.out.println("Failed - post action");
        }
    },
    BLOCKED(3) {

        @Override
        void preAction() {
            System.out.println("Blocked - pre action");
        }

        @Override
        void postAction() {
            System.out.println("Blocked - post action");
        }
    },
    ROLLBACK_SCHEDULED(4) {

        @Override
        void preAction() {
            System.out.println("Rollback scheduled - pre action");
        }

        @Override
        void postAction() {
            System.out.println("Rollback scheduled - post action");
        }
    },
    ROLLBACKED(5) {

        @Override
        void preAction() {
            System.out.println("Rollbacked - pre action");
        }

        @Override
        void postAction() {
            System.out.println("Rollbacked - post action");
        }
    },
    COMPLETED(6) {

        @Override
        void preAction() {
            System.out.println("Completed - pre action");
        }

        @Override
        void postAction() {
            System.out.println("Completed - post action");
        }
    };

    private final int index;

    State(int index) {
        this.index = index;
    }

    // before entering state
    abstract void preAction();

    // before exiting state
    abstract void postAction();
}
