package LeetCode;

import java.util.HashMap;
import java.util.HashSet;

public class ValidAnagram {

    static String s = "aacc";
    static String t = "ccac";

    public static void bruteForce(String s, String t) {
        // Failed
        HashMap<Integer, Character> hashmap = new HashMap<>();
        int count = 0;
        if (s.length() == t.length()) {
            for (int i = 0; i < s.length(); i++) {
//            System.out.println(s.charAt(i));
                hashmap.put(i, s.charAt(i));
            }

            for (int i =0; i < t.length(); i++) {
                if (hashmap.containsValue(t.charAt(i))) {
                    System.out.println("match: " + t.charAt(i));
                    count++;
                }
                System.out.println(count);
                if (count == t.length()) {
                    System.out.println("its an anagram");
                }
            }
        }

        System.out.println(hashmap);

    }

    public static void main(String[] args) {
        bruteForce(s, t);
    }
}
