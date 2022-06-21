package LeetCode;

import java.util.HashMap;
import java.util.PriorityQueue;

public class FirstUniqueChar {

    public static String s = "loveleetcode"; // "leetcode" "aabb"

    public static int bruteForce(String s) {

        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            // If hashmap contains character, increase frequency by 1
            if (hashMap.containsKey(s.charAt(i))) {
                hashMap.put(s.charAt(i), hashMap.get(s.charAt(i)) + 1);
            }
            else {
                hashMap.put(s.charAt(i), 1);
            }
        }
//        System.out.println(hashMap);

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (Character entry : hashMap.keySet()) {
            // Select unique characters - frequency of 1
            if (hashMap.get(entry) == 1) {
                // Add the index of the characters in the string to a priority, from smallest to largest
                queue.add(s.indexOf(entry));
                System.out.println("Index: " + s.indexOf(entry) + " Character: " + entry);
            }
        }
        if (queue.size() != 0) {
            // Return first result of the queue which will be the first index of a unique character
            return queue.poll();
        }
        else return -1;
    }

    public static void main(String[] args) {
        System.out.println(bruteForce(s));
    }
}
