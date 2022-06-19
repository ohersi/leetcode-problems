package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class LongestConsecutiveSequence {

    public static int[] nums = {4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3};
    public static int[] arr = {4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3};
    public static int bruteForce(int[] nums) {
        // Failed
            if (nums.length == 1) {
                return 1;
            }
            else if (nums.length < 1) {
                return nums.length;
            }
//        Arrays.sort(nums);
        // Insertion sort algorithm
        for (int i = 1; i < nums.length; i++) {
            int key = nums[i];
            int j = i - 1;

            // Move elements of nums[0..i-1] that are greater than key to one position ahead of their current position
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j = j - 1;
            }
            nums[j + 1] = key;
        }

        HashSet<Integer> hashSet = new HashSet<>();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
           hashSet.add(nums[i]);
        }

        for (int entry : hashSet) {
           if (hashSet.contains((entry) + 1) || hashSet.contains((entry) - 1)) {
               count += 1;
           }
        }

//        System.out.println(hashSet);
//        System.out.println(count);

        return count;
    }

    public static int neighbors(int[] nums) {

        // Add all elements into hashset, removing duplicate elements
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int longestSequence = 0;

        for (int entry : set) {
            // If entry does not have a neighbor before it (entry - 1) in the set, it is the start of a sequence
            if (!set.contains((entry) - 1)) {
                // length of sequence gets reset after each entry -> the current longest sequence is stored in the longestSequence variable
                int lengthOfSequence = 1;
                System.out.println("Entry: " + entry);
                // Check if neighbor after it (entry + lengthOfSequence) exists; increase length by 1 every time
                while (set.contains((entry) + lengthOfSequence)) {
                    lengthOfSequence += 1;
                    System.out.println("lengthOfSeqeunce: " + lengthOfSequence);
                }
                // Check if current length of sequence is greater than the longest sequence, if so replace longest with current sequence
                if (lengthOfSequence > longestSequence) {
                    longestSequence = lengthOfSequence;
                }
            }
        }
//        System.out.println(longestSequence);
        return longestSequence;
    }

    public static void main(String[] args) {
//        System.out.println(bruteForce(nums));
        System.out.println(neighbors(nums));
    }
}
