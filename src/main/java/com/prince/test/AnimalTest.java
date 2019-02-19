package com.prince.test;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class AnimalTest {

    public static void main(String[] args) {
        AnimalList<Animal> animalList = new AnimalList<>();

        Animal a1 = new Animal();
        a1.setName("A");
        a1.setColor("red");

        Animal a2 = new Animal();
        a2.setName("B");
        a2.setColor("green");

        Animal a3 = new Animal();
        a3.setName("C");
        a3.setColor("blue");

        animalList.add(a1);
        animalList.add(a2);
        animalList.add(a3);

        Iterator<Animal> iterator = animalList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // ----------------------------------------------

        AnimalList<Dog> dogList = new AnimalList<>();
        Dog d1 = new Dog();
        d1.setName("A1");
        d1.setColor("red");
        d1.setBark("boo1");

        Dog d2 = new Dog();
        d2.setName("B1");
        d2.setColor("blue");
        d2.setBark("boo2");

        dogList.add(d1);
        dogList.add(d2);

        Iterator<Dog> dogIterator = dogList.iterator();
        while (dogIterator.hasNext()) {
            System.out.println(dogIterator.next());
        }
    }

    private static class AnimalList<T> {
        private List<T> animals = new ArrayList<>();

        public void add(T animal) {
            animals.add(animal);
        }

        Iterator<T> iterator() {
            return new AnimalIterator(this.animals);
        }
    }

    private static class AnimalIterator<T> implements Iterator<T> {

        private int curIndex = 0;

        private final List<T> animals;

        public AnimalIterator(List<T> animals) {
            this.animals = animals;
        }

        @Override
        public boolean hasNext() {
            return curIndex < animals.size();
        }

        @Override
        public T next() {
            T animal = animals.get(curIndex);
            curIndex += 1;

            return animal;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    private static class Dog extends Animal {

        private String bark;
    }

    @Data
    private static class Animal {

        private String name;

        private String color;

    }
}
