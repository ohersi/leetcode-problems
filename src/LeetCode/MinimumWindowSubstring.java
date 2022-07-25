package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MinimumWindowSubstring {

    public static String s = "cabwefgewcwaefgcf";
    public static String t = "cae";

    public static void bruteForce(String s, String t) {

        // FAILED - Poor optimization -  "265 / 266 test cases passed. Status: Time Limit Exceeded"
        // right is reset to i after every loop, shrinking the window to 0. This is the cause of poor time optimization

        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> seen = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            if (map.containsKey(t.charAt(i))) {
                map.put(t.charAt(i), map.get(t.charAt(i)) + 1);
            }
            else {
                map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 1));
            }
        }

        int right;
        int counter = 0;
        String minString = "";
        // Integer.MAX_VALUE represents the maximum positive integer value that can be represented in 32 bits
        Integer windowSize = Integer.MAX_VALUE;
        ArrayList<Integer> window = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            // Reset right to ith position
            right = i;
            if (map.containsKey(s.charAt(i))) {
                while (right < s.length()) {
                    if (map.containsKey(s.charAt(right))) {
                        // Add to seen hashmap
                        if (seen.containsKey(s.charAt(right))) {
                            // Check to see whether char is duplicate
                            // E.g. MOON - O is encountered, value of 2 in map...
                            // Check if seen char value is less than 2, if it is less than 2 it is NOT a duplicate and can add +1 to seen value
                           if (seen.get(s.charAt(right)) < map.get(s.charAt(right))) {
                               // Non-unique char is NOT a duplicate so counter is increased
                               counter++;
                           }
                            seen.put(s.charAt(right), seen.get(s.charAt(right)) + 1);
                        }
                        else {
                            // Char is not in the seen hashmap, so add it and increase counter since it is one of the unique chars
                            seen.put(s.charAt(right), seen.getOrDefault(s.charAt(right), 1));
                            counter++;
                        }
                        ////////////////////////////////////////////////////////////////////////////////////////
                        // Once counter is equal to t length, get indices and window size
                        if (counter == t.length()) {
                            int[] result = new int[]{i, right};
                            System.out.println(Arrays.toString(result));
                            // Check if current window size is less than windowSize; looking for min windowSize
                            if ((right - i) + 1 < windowSize) {
                                // Clear arraylist and add both indices
                                window.clear();
                                window.add(i);
                                window.add(right);
                                // Update windowSize
                                windowSize = (right - i) + 1 ;
                            }
                            break;
                        }
                    }
                    right++;
                }
                System.out.println("_______________");
            }
            System.out.println("right " + right);
            // Reset seen hashmap and counter
            counter = 0;
            seen.clear();
        }

//        if (!window.isEmpty()) {
//            for (int i = window.get(0); i <= window.get(1); i++) {
//            minString += s.charAt(i);
//        }
//        }

        if (!window.isEmpty()) {
            minString = s.substring(window.get(0),window.get(1) + 1);
        }
        System.out.println(window);
        System.out.println("windowSize: " + windowSize);
        System.out.println(minString);
    }

    public static void pointers(String s, String t) {

        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> seen = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            if (map.containsKey(t.charAt(i))) {
                map.put(t.charAt(i), map.get(t.charAt(i)) + 1);
            }
            else {
                map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 1));
            }
        }


        int left = 0;
        int right = 0;
        int counter = map.size();
        int windowSize = s.length();
        int start = 0;
        int end = 0;
        boolean found = false;
        String minString = "";

        while (right < s.length()) {

            if (map.containsKey(s.charAt(right))) {
                map.put(s.charAt(right), map.get(s.charAt(right)) - 1);
                if (map.get(s.charAt(right)) == 0) {
                    counter -= 1;
                }
            }
            right++;

            if (counter > 0)  continue;

            while (counter == 0) {
//                System.out.println("inside counter == 0");
                if (map.containsKey(s.charAt(left))) {
                    map.put(s.charAt(left), map.get(s.charAt(left)) + 1);
                    if (map.get(s.charAt(left)) > 0) {
                        counter += 1;
                    }
                }
                left++;
            }

            if ((right - left) < windowSize) {
                start = left;
                end = right;
                windowSize = right - left;
                found = true;
            }

            if (found == true) {
                minString = (s.substring(start - 1, end));
            }
        }
        System.out.println(minString);
    }

    public static void main(String[] args) {
        bruteForce(s, t);
        System.out.println("_____END______");
        pointers(s, t);
    }
}
