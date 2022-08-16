package LeetCode;

public class FindMinimumInRotatedSortedArray {

    public static int[] nums = {4,5,6,7,0,1,2};

    public static int search(int[] nums) {

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {

            int midpoint = low + (high - low) / 2;

            if (low == midpoint && high == midpoint) return nums[midpoint];

            System.out.println("BEFORE - low: " + nums[low] + " midpoint: " + nums[midpoint] + " high: " + nums[high]);

            // Leftmost index is less than midpoint - min # could be in left portion of sorted array
            if (nums[low] <= nums[midpoint]) {
                // If leftmost index is greater than rightmost index; min # cannot be between leftmost and midpoint
                    // E.g. [3, 4, 5, 1, 2] ---> Since leftmost index less than midpoint, that portion (left) of the array is sorted
                        // 3 to 5 portion cannot contain the min # if the right portion [0, 1, 2] ...
                        // contains a # smaller than the smallest # in the left portion
                if (nums[low] > nums[high]) {
                    // Shift search to midpoint + 1 and rightmost index
                    low = midpoint + 1;
                    System.out.println("low moving to midpoint + 1");
                }
                // Leftmost index is less rightmost index; sorted portion of array - min # can be there
                    // E.g. [0, 1, 2] ---> min # is somewhere in that search
                else {
                    // Shift search to leftmost index and midpoint
                    // Include midpoint since it could contain min #
                    System.out.println("high moving to midpoint");
                    high = midpoint;
                }
            }
            // Leftmost index is greater than midpoint
                // E.g. [5, 1, 2, 3, 4] ---> [[5, 1, 2], 3, 4]; min # could see be in this unsorted portion
            else {
                // If midpoint is less than rightmost index; sorted array portion
                // Since midpoint is also less than leftmost index...
                    // min # cannot be between midpoint and rightmost index ---> [5, 1, [2 , 3 , 4]]
                if (nums[midpoint] < nums[high]) {
                    high = midpoint;
                    System.out.println("midpoint less than rightmost index; moving high to midpoint");
                }
                // Midpoint is greater than rightmost index; unsorted portion
                // E.g. [5, 3, 2] ---> Both midpoint and leftmost index are greater than rightmost index
                    // min # cannot be before midpoint; shift search to every past midpoint
                else {
                    low = midpoint + 1;
                    System.out.println("midpoint greater than rightmost index; moving low to midpoint + 1");
                }
            }
            System.out.println("AFTER - low: " + nums[low] +  " midpoint: " + nums[midpoint] + " high: " + nums[high]);
            System.out.println("__________________");
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println("result is: " + search(nums));
    }
}
