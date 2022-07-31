package LeetCode;

import java.util.*;

public class SlidingWindowMaximum {

    public static int[] nums = {5, 3, 4, 5};
    public static int k = 2;

    public static int[] deque(int[] nums, int k) {

        Deque<Integer> deque = new ArrayDeque<>(); // store nums indices

        // size of nums array - the window size (k) + 1
            // E.g. nums.length = 6, k = 2   + 1 ---> 5 amount of times the window can shift inside the nums array
        int[] array = new int[nums.length - k + 1];

        int left = 0; // distance between left and right (left+right+1) should be size k; they represent the window
                            // represent the indices in nums, left & right endpoints of the window

        for (int right = 0; right < nums.length; right++) {
            // If queue is not empty, check if nums[current number] is greater than the nums[index of last element in queue]
            while (!deque.isEmpty() && nums[right] > nums[deque.peekLast()]) {
                // If true, remove last element until condition is no longer true
                deque.removeLast();
            }
            // Add index of current num to queue
            deque.addLast(right);

            // If left endpoint of the window is greater than the index (of nums) at the front of the queue
            // E.g. left = 3, queue = [0, 1, 2, 3, 4, 5, 6] ---> queue = [3, 4, 5, 6]
            if (!deque.isEmpty() && left > deque.getFirst()) {
//              System.out.println("Left is: " + left + " is greater than " + deque.getFirst());
                // Remove the first element in the queue
                deque.removeFirst();
            }
            // If distance between endpoints is greater/equal to window size (k)
            if ((right + left) + 1 >= k) {
                // Add the first element in the queue to the array list
                array[right - k + 1] = nums[deque.peekFirst()];
//                System.out.println("right: " + right + " - k: " + k + " + 1 = " + (right - k + 1) );
//                System.out.println("adding: " + deque.getFirst() + " to array");

                // Increment the left endpoint; shifting the window over by 1
                left++;
            }
        }
        return array;
    }

    public static int[] max(int[] nums, int k){

        // FAILED ---> Input: [1, -1] - Output: [1,1] - Expected [1, -1]

        if (nums.length == 0) return new int[] {};

        ArrayList<Integer> arrayList = new ArrayList<>();

        int max = 0;

        for (int i = 0; i < k; i++) {
            max = Math.max(max, nums[i]);
        }
        arrayList.add(max);

        for (int i = k; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            arrayList.add(max);
        }

        if (!arrayList.isEmpty()) {

            int[] result = new int[arrayList.size()];

            for (int i = 0; i < arrayList.size(); i++) {
                result[i] = arrayList.get(i);
            }
            return result;
        }
        else {
            return new int[] {};
        }
    }

    public static int[] bruteForce(int[] nums, int k) {
        // FAILED - Time Limited Exceeded - not scalable for larger inputs
        // 0 (N * K) ---> Nested loop; checking k elements (window) for every n (each number in array)

        if (nums.length == 0) return new int[] {};

        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i <= nums.length - k; i++) {

            int max = nums[i];
            int left = i;
            int right = i + k;

            while (left < right) {
                max = Math.max(max, nums[left]);
                left++;
            }
            arrayList.add(max);
            System.out.println("________");
        }

        if (!arrayList.isEmpty()) {
            int[] results = new int[arrayList.size()];

            for (int i = 0; i < arrayList.size(); i++) {
                results[i] = arrayList.get(i);
            }
            return results;
        }
        else {
            return new int[] {};
        }
    }


    public static void main(String[] args) {
//        System.out.print("results: " + Arrays.toString(max(nums, k)));
//        System.out.print("results: " + Arrays.toString(bruteForce(nums, k)));
        System.out.print("results: " + Arrays.toString(deque(nums, k)));
    }
}
