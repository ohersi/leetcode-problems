package LeetCode;

import java.util.HashSet;

public class LongestSubstringWithoutRepeatingChars {

    public static String s = "abcabcbb";
    public static void bruteForce(String s) {
        // Time complexity 0(N) - length of string, chars are visited once
        // Space complexity 0(1) - max size of hashset depends on character set in string (e.g. lowercase - 26, uppercase - 26, digits - 9); does not scale

        int start = 0;
        int end = start;
        int max = 0;
        HashSet<Character>  set = new HashSet<>();

        while (end < s.length()) {
            // If set contains repeating character, remove the starting character - shrinking the window from the left
            if (set.contains(s.charAt(end))) {
                set.remove(s.charAt(start));
                // Increment start pointer; sliding the window over
                start++;
            }
            else {
                // Add non duplicate chars
                set.add(s.charAt(end));
                // Increment end pointer; increase size of window
                end++;
            }
            // Maximum length of start and end pointers substring
            max = Math.max(max, end - start);
        }
        System.out.println(max);
    }

    public static void main(String[] args ) {
        bruteForce(s);
    }
}
