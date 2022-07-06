package LeetCode;

import java.util.HashMap;

public class LongestRepeatCharReplacement {

    public static String s = "ABAB";
    public static int changesAllotted = 2;

    public static int bruteForce(String s, int changesAllotted) {

        // Store the characters and their frequency
        HashMap<Character, Integer> map = new HashMap<>();

        int left = 0;
        int right = left;
        int windowSize;
        int largestFrequency = 0;
        int longestSubstring = 0;

        while (right < s.length()) {
            // If map contains char, increase frequency
            if (map.containsKey(s.charAt(right))) {
                map.put(s.charAt(right), map.get(s.charAt(right)) + 1);
            }
            // Else add new char to map, default is 1 since it is the first time the char has been seen
            else {
                map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 1));
            }
            // Get the highest frequency, updating the variable after every loop
            largestFrequency = Math.max(largestFrequency, map.get(s.charAt(right)));
//            System.out.println("largestFrequency: " + largestFrequency);

            // Length of left and right pointers; size of window
            windowSize = (right - left) + 1;
//            System.out.println("windowSize: " + windowSize);

            // The size of the window - most frequent char = HOW MANY CHARS NEED TO REPLACED FOR ALL CHARS TO MATCH
                //E.g. BABB - windowSize = 4; B is most frequent with 3; replace 1 char --> 1 <= 2 (the constraint)

            // Check if the # of characters that need to be replaced in the window is less than or equal to the allotted constraint
            if (windowSize - largestFrequency <= changesAllotted) {
                // Since # of replacements in the window is less/equal to the constraint...
                // the window size is the max length of the longest substring containing the same letter
                //E.g. BABB ---> BBBB ---> 1 char changed & it's less than constraint; size of substring is 4.
                longestSubstring = Math.max(longestSubstring, windowSize);
            }
            else {
                // Move left pointer over, decrease frequency by 1
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                left++;
            }
            right++;
        }
        System.out.println(map);
        return longestSubstring;
    }

    public static void main(String[] args) {
        System.out.println("The longest substring is: " + bruteForce(s, changesAllotted));

    }
}
