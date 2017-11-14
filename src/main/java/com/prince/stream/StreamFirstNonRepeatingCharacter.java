package com.prince.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Prince Raj
 */
public class StreamFirstNonRepeatingCharacter {

    private Map<Character, Integer> characterCountMap = new HashMap<>();

    private List<Character> nonRepeatingCharacters = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        boolean stdIn = false;

        StreamFirstNonRepeatingCharacter stream = new StreamFirstNonRepeatingCharacter();

        if (stdIn) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s;
            while ((s = br.readLine()) != null) {
                if ("done".equals(s)) {
                    break;
                }
                System.out.println("First non repeating character: "
                        + stream.getFirstNonRepeatingCharacter(s.toCharArray()[0]));
            }
        } else {
            char[] arr = getCharacters();

            for (char x : arr) {
                System.out.println("First non repeating character: " + stream.getFirstNonRepeatingCharacter(x));
            }
        }
    }

    private Character getFirstNonRepeatingCharacter(Character c) {
        Integer count = characterCountMap.get(c);
        if (count == null) {
            nonRepeatingCharacters.add(c);
            count = 1;
        } else {
            count = count + 1;
        }
        characterCountMap.put(c, count);

        if (count > 1) {
            nonRepeatingCharacters.remove(c);
        }

        final Character firstNonRepeatingCharacter;
        if (nonRepeatingCharacters.size() > 0) {
            firstNonRepeatingCharacter = nonRepeatingCharacters.get(0);
        } else {
            firstNonRepeatingCharacter = null;
        }

        return firstNonRepeatingCharacter;
    }

    private static char[] getCharacters() {
        return new char[] {
                'g',
                'e',
                'e',
                'k',
                's',
                'f',
                'o',
                'r',
                'g',
                'e',
                'e',
                'k',
                's',
                'a',
                'n',
                'd',
                'g',
                'e',
                'e',
                'k',
                's'};
    }
}
