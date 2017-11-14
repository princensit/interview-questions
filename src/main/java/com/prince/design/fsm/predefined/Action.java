package com.prince.design.fsm.predefined;

/**
 * @author Prince Raj
 */
public enum Action {

    A(0) {

        @Override
        void execute() {
            System.out.println("Executing action A");
        }
    },
    B(1) {

        @Override
        void execute() {
            System.out.println("Executing action B");
        }
    },
    C(2) {

        @Override
        void execute() {
            System.out.println("Executing action C");
        }
    },
    D(3) {

        @Override
        void execute() {
            System.out.println("Executing action D");
        }
    },
    E(4) {

        @Override
        void execute() {
            System.out.println("Executing action E");
        }
    },
    F(5) {

        @Override
        void execute() {
            System.out.println("Executing action F");
        }
    },
    G(6) {

        @Override
        void execute() {
            System.out.println("Executing action G");
        }
    },
    H(7) {

        @Override
        void execute() {
            System.out.println("Executing action H");
        }
    },
    I(8) {

        @Override
        void execute() {
            System.out.println("Executing action I");
        }
    },
    J(9) {

        @Override
        void execute() {
            System.out.println("Executing action J");
        }
    },
    K(10) {

        @Override
        void execute() {
            System.out.println("Executing action K");
        }
    };

    private final int index;

    Action(int index) {
        this.index = index;
    }

    abstract void execute();
}
