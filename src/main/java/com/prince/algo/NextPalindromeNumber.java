package com.prince.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Prince Raj
 */
public class NextPalindromeNumber {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());

        for (int i = 0; i < cases; i++) {
            int size = Integer.parseInt(br.readLine());
            String[] arrStr = br.readLine().split(" ");
            int[] arr = new int[size];

            for (int j = 0; j < size; j++) {
                arr[j] = Integer.parseInt(arrStr[j]);
            }

            nextPalindrome(arr);
            System.out.println();
        }
    }

    private static void nextPalindrome(int[] arr) {
        int length = arr.length;

        boolean all9s = all9s(arr);
        if (all9s) {
            System.out.print("1 ");
            for (int i = 1; i < arr.length; i++) {
                System.out.print("0 ");
            }
            System.out.print("1");
        } else if (length == 1) {
            System.out.print(arr[0] + 1);
        } else {
            int p1;
            int p2;

            boolean even = false;
            p1 = length / 2 - 1;
            if (length % 2 == 0) {
                p2 = p1 + 1;
                even = true;
            } else {
                p2 = p1 + 2;
            }

            while (p1 >= 0 && p2 < length) {
                if (arr[p1] == arr[p2]) {
                    p1--;
                    p2++;
                } else if (arr[p1] > arr[p2]) {
                    arr[p2] = arr[p1];
                    p1--;
                    p2++;
                    break;
                } else {
                    int mid;
                    if (even) {
                        mid = length / 2 - 1;
                    } else {
                        mid = length / 2;
                    }

                    // 89 or 889
                    if (arr[mid] != 9) {
                        arr[mid] += 1;
                    } else {
                        // 199997 or 19997
                        arr[mid] = 0;
                        while (arr[--mid] == 9) {
                            arr[mid] = 0;
                        }
                        arr[mid] += 1;
                    }

                    p1 = length / 2 - 1;
                    if (even) {
                        p2 = p1 + 1;
                    } else {
                        p2 = p1 + 2;
                    }
                    break;
                }
            }

            while (p1 >= 0) {
                arr[p2++] = arr[p1--];
            }

            for (int elem : arr) {
                System.out.print(elem + " ");
            }
        }
    }

    private static boolean all9s(int[] arr) {
        boolean all9s = true;
        for (int elem : arr) {
            if (elem != 9) {
                all9s = false;
                break;
            }
        }

        return all9s;
    }
}
