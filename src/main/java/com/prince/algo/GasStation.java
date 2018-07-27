package com.prince.algo;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * https://www.geeksforgeeks.org/find-a-tour-that-visits-all-stations/
 *
 * @author Prince Raj
 */
public class GasStation {

    public static void main(String[] args) {
        int[] petrol = new int[] {4, 6, 7, 4};
        int[] nextPetrolPumpDistance = new int[] {6, 5, 3, 5};
        System.out.println("Starting point: " + getStartingPoint(petrol, nextPetrolPumpDistance));

        petrol = new int[] {1, 1};
        nextPetrolPumpDistance = new int[] {6, 5};
        System.out.println("Starting point: " + getStartingPoint(petrol, nextPetrolPumpDistance));

        petrol = new int[] {6, 3, 7};
        nextPetrolPumpDistance = new int[] {4, 6, 3};
        System.out.println("Starting point: " + getStartingPoint(petrol, nextPetrolPumpDistance));

        petrol = new int[] {684, 57, 602, 987};
        nextPetrolPumpDistance = new int[] {909, 535, 190, 976};
        System.out.println("Starting point: " + getStartingPoint(petrol, nextPetrolPumpDistance));
    }

    private static int getStartingPoint(int[] petrol, int[] nextPetrolPumpDistance) {
        if (petrol == null || petrol.length == 0) {
            return -1;
        }

        int length = petrol.length;
        if (length < 2) {
            return 0;
        }

        // Instead of creating a separate queue, we can use the given array itself as queue. We maintain two index
        // variables start and end that represent rear and front of queue.
        Deque<Integer> queue = new ArrayDeque<>();

        int currentPetrol = 0;
        queue.add(0);

        Set<Integer> petrolPumpsVisited = new HashSet<>();
        petrolPumpsVisited.add(0);

        while (queue.size() != length) {
            int lastIndex = queue.getLast();
            if (currentPetrol + petrol[lastIndex] < nextPetrolPumpDistance[lastIndex]) {
                int index = queue.removeFirst();
                currentPetrol -= petrol[index];
                if (currentPetrol < 0) {
                    currentPetrol = 0;
                }

                if (queue.isEmpty()) {
                    int startIndex = (lastIndex + 1) % length;
                    if (petrolPumpsVisited.contains(startIndex)) {
                        // already visited this petrol pump as starting point

                        queue.clear();
                        queue.add(-1);
                        break;
                    } else {
                        queue.add(startIndex);
                        petrolPumpsVisited.add(startIndex);
                    }
                }
            } else {
                currentPetrol = currentPetrol + petrol[lastIndex] - nextPetrolPumpDistance[lastIndex];
                queue.add((lastIndex + 1) % length);
            }
        }

        // return first element from queue
        return queue.getFirst();
    }
}
