package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public static int[] nums = {-1,0,1,2,-1,-4};
    public static void bruteForce(int[] nums) {
        // i, j, k
        // i != j --- i != k ---- j != k ----> cannot share same indices
        // nums[i] + nums[j] + nums[k] == 0
        // i = i, j = left_pointer, k = right_pointer

        Arrays.sort(nums);      //[-4, -1, -1, 0, 1, 2]
        ArrayList<List<Integer>> arrayList = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            // Check if previous number in the array is the same as current, if so skip to next i in the loop
                // Prevents duplicate triplets in the list of arrays
                    // E.g. [[-1, -1, 2], [-1, 0, 1], [-1, 0, 1]] ---> the triplet [-1, 0, 1] shows up twice
            if (i == 0 || i > 0 && nums[i] != nums[i - 1]) {

                int left_pointer = i + 1;
                int right_pointer = nums.length - 1;

                while (left_pointer < right_pointer) {

                    System.out.println("Checking -- " + " nums[i]: " + nums[i] + "  AND i is: " + i + " -- nums[left_pointer]: " + nums[left_pointer] + " AND left_pointer: " + left_pointer + " -- nums[right_pointer]: " +  nums[right_pointer] + " AND right_pointer: " + right_pointer);

                    int sum = nums[i] + nums[left_pointer] + nums[right_pointer];
                    // Check if all three numbers added = 0
                    if (sum == 0) {
                        // Add to arraylist
                        ArrayList<Integer> matches = new ArrayList<>();
                        matches.addAll(Arrays.asList(nums[i], nums[left_pointer], nums[right_pointer]));
                        arrayList.add(matches);
                        System.out.println("Triplet found: " + matches);
                        // Move left_pointer forwards by 1
                        left_pointer++;
                        // Move left_pointer backwards by 1
                        right_pointer--;
                        // Check for duplicate numbers
                        // Check if current number in left_pointer is same as previous number, if so continue incrementing left_pointer until they are different
                        while (nums[left_pointer] == nums[left_pointer - 1] && left_pointer < right_pointer) {
                            left_pointer++;
                        }
                        // Check if current number in right_pointer is same as next number, if so continue decrementing right_pointer until they are different
                        while (nums[right_pointer] == nums[right_pointer + 1] && left_pointer < right_pointer) {
                            right_pointer--;
                        }
                    }
                    // If sum of all numbers is greater than 0, decrement; right_pointer is the larger of the two pointers, need smaller number to get to 0
                    else if (sum > 0) {
                        right_pointer--;
                    }
                    // If sum of all numbers is less than 0, increment; left_pointer is the smaller of the two pointers, need larger number to get to 0
                    else if ( sum < 0) {
                        left_pointer++;
                    }
                }
            }
        }
        System.out.println("Final result: " + arrayList);
    }

    public static void main(String[] args) {
        bruteForce(nums);
    }
}
