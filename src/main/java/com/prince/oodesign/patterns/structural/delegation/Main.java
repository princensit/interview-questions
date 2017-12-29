package com.prince.oodesign.patterns.structural.delegation;

/**
 * Support "composition over inheritance"
 *
 * @author Prince Raj
 */
public class Main {

    public static void main(String[] args) {
        A a = new A();

        B b = new B(a);
        b.foo();
        b.bar();
    }
}

class A {

    public void foo() {
        this.bar();
    }

    public void bar() {
        System.out.println("A.bar");
    }
}

class B {

    private A a;

    public B(A a) {
        this.a = a;
    }

    public void foo() {
        a.foo();
    }

    public void bar() {
        System.out.println("B.bar");
    }
}