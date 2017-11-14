package com.prince.oodesign.patterns.creational.builder;

/**
 * @author Prince Raj
 */
public class Main {

    public static void main(String[] args) {
        User user = new User.UserBuilder("Rahul", "Jain").age(30).phone("1234567").address("Fake address 1234").build();
        System.out.println(user);
    }
}
