package LeetCode;

public class SearchRotatedSortedArray {

    public static int[] nums = {4,5,6,7,0,1,2};
    public static int target = 0;

    public static int search(int[] nums, int target) {

        //BINARY SEARCH

        int low = 0;
        int high = nums.length - 1;

        // Sorted array is split into two parts at from pivot (k) and trade places
            // [0,1,2,4,5,6,7] shifted at index 3 (4) ---> [0, 1, 2] x [4, 5, 6, 7] --> {4, 5, 6, 7, 0, 1, 2]

        while (low <= high) {

            int midpoint = low + (high - low) / 2;

            // pointers end on final index - search over
            if (target == nums[midpoint]) return midpoint;

            System.out.println("low: " + nums[low] + " midpoint: " + nums[midpoint] + " high: " + nums[high]);

            // Leftmost index is less than midpoint - left portion of sorted array
            if (nums[low] <= nums[midpoint]) {
                // Target is between leftmost index and midpoint - target is somewhere in middle
                if (target >= nums[low] && target < nums[midpoint]) {
                    // Shift search: low to midpoint
                    high = midpoint - 1;
                }
                // Target is not between leftmost index and midpoint - shift search to midpoint and high
                else {
                    low = midpoint + 1;
                    System.out.println("low: " + nums[low] + " midpoint: " + nums[midpoint] + " high: " + nums[high]);
                }
            }
            // Target # is greater than midpoint - might be in the right portion; check everything after midpoint
            else {
                // Target is between the midpoint and the rightmost index - target is somewhere in the middle of the two
                if (target > nums[midpoint] && target <= nums[high]) {
                    // Shift search between midpoint and rightmost index
                    low = midpoint + 1;
                    System.out.println("low: " + nums[low] + " midpoint: " + nums[midpoint] + " high: " + nums[high]);
                }
                // Target is not between the midpoint and rightmost index - shift search to low and midpoint
                else {
                    high = midpoint - 1;
                    System.out.println("low: " + nums[low] + " midpoint: " + nums[midpoint] + " high: " + nums[high]);
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(nums, target));
    }
}

