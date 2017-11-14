package com.prince.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
 *
 * @author Prince Raj
 */
public class LargestRectangularArea {

    private static Stack<Integer> indexesStack = new Stack<>();

    public static void main(String[] args) throws Exception {
        boolean stdIn = true;

        if (stdIn) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int noOfCases = Integer.parseInt(br.readLine());

            int i = 0;
            while (i < noOfCases) {
                int count = Integer.parseInt(br.readLine());
                String[] arrStr = br.readLine().split(" ");
                int[] barHeights = new int[count];

                for (int j = 0; j < arrStr.length; j++) {
                    barHeights[j] = Integer.parseInt(arrStr[j]);
                }

                printLargestArea(barHeights);
                System.out.println();

                i++;
            }
        } else {
            int[] barHeights = {6, 2, 5, 5, 4, 5, 5, 1, 6};

            printLargestArea(barHeights);
        }
    }

    private static void printLargestArea(int[] barHeights) {
        int maxArea = 0;

        int i = 0;
        while (i < barHeights.length) {
            if (indexesStack.empty() || barHeights[indexesStack.peek()] <= barHeights[i]) {
                indexesStack.push(i++);
            } else {
                maxArea = getMaxArea(barHeights, maxArea, i);
            }
        }

        while (!indexesStack.isEmpty()) {
            maxArea = getMaxArea(barHeights, maxArea, i);
        }

        System.out.print(maxArea);
    }

    private static int getMaxArea(int[] barHeights, int maxArea, int i) {
        int area;
        int top = indexesStack.pop();
        area = barHeights[top] * (indexesStack.isEmpty() ? i : (i - indexesStack.peek() - 1));

        if (area > maxArea) {
            maxArea = area;
        }
        return maxArea;
    }

}