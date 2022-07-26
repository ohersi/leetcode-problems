package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PermutationInString {

    public static String s1 = "ab";
    public static String s2 = "eidbaooo"; //eidboaoo

    public static boolean pointers(String s1, String s2) {

        // Since s2 must contain a permutation of substring s1, s1 cannot be larger than s2.
//        if (s1.length() > s2.length() || s2.length() == 0) return false;
        if (s1.length() == 0) return true;

        // ASCII values --> 'a' to 'z' - 26 characters
        int[] array = new int[26];
        int[] array2 = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            // In the array, increase the frequency for every s1 char using the alphabetical position of each char
            // E.g. 'b' is the second letter in the alphabet, index should be 1
                // 'b' ASCII value is 98 ---> s1.char(i) = 'b' - 'a' ---> 98 - 97 =  1
                    // Increase frequency in index 1 by 1;
            array[s1.charAt(i) - 'a']++;
            // The second array is the frequency of chars in the sliding window of s2 and will be compared with first array
            // Loop through the length of s1 since it is the size of the sliding window, adding s2 char (so first 2 chars of s2 ---> e & i)
            array2[s2.charAt(i) - 'a']++;
        }
//        System.out.println(Arrays.toString(array2));
        // array = [1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]; ---> a = 1, b = 1;
        // array2 = [0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]; ---> e = 1; i = 1;


        int n = s1.length();

        // Since array2 already contains the initial n chars of s2 (n being the length of s1), check all chars afters
            // Start the loop at the first char not in array2, sliding the window by 1;
                //E.g. First char not in initial array2 is 'd'
                    // Window starts at 'd', it is added and 'e' is removed ---> [e, i] ----> [i, d]
        for (int i = n; i < s2.length(); i++) {

            // Checking if the arrays are equal
            if (Arrays.equals(array, array2)) {
                   return true;
                }

            // Adding frequency of current char in the window
            array2[s2.charAt(i) - 'a']++;
            System.out.println("adding char from window: " + s2.charAt(i));

            // Decreasing frequency of first char in the window
            array2[s2.charAt(i - n) - 'a']--;
            System.out.println("removing from window: " + s2.charAt(i - n));
        }

        // Check if final array and array2 are equal
        if (Arrays.equals(array, array2)) {
            return true;
        }

        return false;
    }

    public static boolean hashmap(String s1, String s2) {

        if (s1.length() > s2.length() || s2.length() == 0) return false;
        if (s1.length() == 0) return true;

        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> windowMap = new HashMap<>();
        int n = s1.length();

        for (int i = 0; i < s1.length(); i++) {
            if (map.containsKey(s1.charAt(i))) {
                map.put(s1.charAt(i), map.get(s1.charAt(i)) + 1);
            }
            else {
                map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i), 1));
            }
            ///////// ADDING CHARS FOR S2 //////////
            if (windowMap.containsKey(s2.charAt(i))) {
                windowMap.put(s2.charAt(i), windowMap.get(s2.charAt(i)) + 1);
            }
            else {
                windowMap.put(s2.charAt(i), windowMap.getOrDefault(s2.charAt(i), 1));
            }
        }

        for (int i = n; i < s2.length(); i++) {
            // Check if both are equal
            if (map.equals(windowMap)) {
                return true;
            }
            // Adding s2 char into window (windowMap)
            if (windowMap.containsKey(s2.charAt(i))) {
                windowMap.put(s2.charAt(i), windowMap.get(s2.charAt(i)) + 1);
                System.out.println("increasing char: " + s2.charAt(i));
            }
            else {
                windowMap.put(s2.charAt(i), windowMap.getOrDefault(s2.charAt(i), 1));
                System.out.println("adding char: " + s2.charAt(i));
            }
            
        // Shifting window, removal of the first char of the window
        // Check if frequency is greater than 1, if so the char cannot be removed from map, instead its frequency is decreased by 1
            if (windowMap.get(s2.charAt(i - n)) > 1) {
                windowMap.put(s2.charAt(i - n), windowMap.get(s2.charAt(i - n)) - 1);
                System.out.println("decreasing char: " + s2.charAt(i - n));
            }
            else {
                windowMap.remove(s2.charAt(i - n));
                System.out.println("removing char: " + s2.charAt(i - n));
            }
            System.out.println(windowMap);
        }
        // Check if final map and windowMap are equal
        if (map.equals(windowMap)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println("The answer is: " + pointers(s1, s2));
        System.out.println("The answer is: " + hashmap(s1, s2));
    }
}
