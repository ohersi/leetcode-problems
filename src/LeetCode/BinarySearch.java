package LeetCode;

public class BinarySearch {

    public static int[] nums = {-1,0,3,5,9,12}; // Sorted in ascending order
    public static int target = 9;

    public static int divideAndConquer(int[] nums, int target) {

        // Time complexity O(log n) Logarithmic time - Dividing the search into smaller and smaller segments

        int low = 0; // left pointer
        int high = nums.length - 1; // right pointer

        while (low <= high) {

            // The middle index between the two pointers
                // E.g. 1 + (5 - 1) / 2 =  3 ---> the midpoint between 1 & 5 is 3
            int midpoint = low + (high - low) / 2;

            if (target == nums[midpoint]) {
                return midpoint;
            }

            // If the target number is greater than the midpoint of the two pointers, move the low pointer to the midpoint
                // The result should be on the right-hand side of the middle, so anything before is irrelevant
            if (target > nums[midpoint]) {
                // midpoint + 1 since the number at midpoint was already checked
                low = midpoint + 1;
            }
            // If the target number is less than the midpoint of the two pointers, move the high pointer to the midpoint
            // The result should be on the left-hand side of the middle, so anything after is irrelevant
            else if (target < nums[midpoint]) {
                // midpoint - 1 since the number at midpoint was already checked
                high = midpoint - 1;
            }

            System.out.println("low: " + nums[low]);
            System.out.println("midpoint: " + nums[midpoint]);
            System.out.println("high: " + nums[high]);
        }

        // If target is not found
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(divideAndConquer(nums, target));
    }
}
