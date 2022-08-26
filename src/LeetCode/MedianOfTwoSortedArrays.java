package LeetCode;

public class MedianOfTwoSortedArrays {

    public static int[] nums1 = {2, 3, 4, 5};
    public static int[] nums2 = {1};

    // GOAL //
    // Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

    // Median formula:
    // If  (m + n) = x is odd ---> x / 2 = median
    // If (m + n) = x is even ---> Combine both arrays in ascending order...
    // get length of combined / 2 = y, go to y position in array and add it and the next # and divide it by 2 ---> (y + z) = median
    public static double counter(int[] nums1, int[] nums2) {

        // Time complexity O(m + n); going through each array once
        // Space complexity O(1); storing median in variable so lookup time is constant

            double median = 0;

            // Pointer for first array
            int nums1pointer = 0;
            // Pointer for second array
            int nums2pointer = 0;

            // Keep track of smaller number between the arrays; when the pointer shifts prev variable will hold the previous smaller # found
            int prev = 0;
            int current = 0;

            // Loop until reach the midpoint of the combined arrays
            for (int i = 0; i <= (nums1.length + nums2.length) / 2; i++) {
                // Keep track of the previous smaller #;
                prev = current;
                System.out.println("prev: " + prev + " is now current: " + current);

                // Reached the end of the first array, continue on with the second
                if (nums1pointer == nums1.length) {
                    current = nums2[nums2pointer];
                    // Incrementing the second array since the first has already ended
                    nums2pointer += 1;
                    System.out.println("nums1pointer == nums1.length; updating current to nums2[nums2pointer]: " + nums2[nums2pointer]);
                }
                // Reached the end of the second array, continue on with the first
                else if (nums2pointer == nums2.length){
                    current = nums1[nums1pointer];
                    // Incrementing the first array since the second has already ended
                    nums1pointer += 1;
                    System.out.println("nums2pointer == nums2.length; updating current to nums1[nums1pointer]: " + nums1[nums1pointer]);
                }
                // If neither pointers have reached end of their arrays...
                // First array # less than second array #
                 else if (nums1[nums1pointer] <= nums2[nums2pointer]) {
                     // nums1 has the smaller #; add it to current
                    current = nums1[nums1pointer];
                    System.out.println("current: " + nums1[nums1pointer] + " and +1 nums1pointer");
                    nums1pointer += 1;
                }
                // First array # greater than second array #
                 else {
                    // nums2 has the smaller #; add it to current
                    current = nums2[nums2pointer];
                    System.out.println("current: " + nums2[nums2pointer] + " and +1 nums2pointer");
                    nums2pointer += 1;
                }
                System.out.println("___________________");
                System.out.println("current: " + current);
                System.out.println("prev: " + prev);
            }

            // Median formula
            if ((nums1.length + nums2.length) % 2 == 0) {
                median = (double) (prev + current )/ 2;
            }
            else {
                median = (double) current;
            }

        return median;
    }
    public static double search(int[] nums1, int[] nums2) {

        // Time complexity O(log(n+m)); using binary search on both arrays, reducing the search in half each time
        // Space complexity O(1);

        // Switching arrays so that nums1 is always smaller than nums2; doing binary search on smaller array

        if (nums1.length > nums2.length) return search(nums2, nums1);

        double median = 0;

        // partition - combined arrays + 1 / 2;
        // Splitting arrays evenly so that both left and right partitions have equal # of elements
            // If combined arrays are odd, one partition will have extra element
        int partition = (nums1.length + nums2.length) / 2;
        System.out.println("partition: " + partition);

        int low = 0;
        // Binary search on the smaller array
        int high = nums1.length;

        // BINARY SEARCH
        while (low <= high) {

            // L1 <= L2 is always true since first array is already sorted
            // R1 <= R2 is always true since second array is already sorted
            // Looking for L1 <= R2 and R1 <= L2; all numbers to the left of the partition are smaller than all numbers on right then both arrays are sorted

            // Partition of the first array; # of elements of the first array on the left side
                // E.g. nums1.length = 4 ---> midpoint = 2 ---> 2 elements on left and 2 elements to the right of the partition
            int midpoint = low + (high - low) / 2;
            // Partition of the second array; # of elements of the second array on the left side
            int cutOff = partition - midpoint;
            // Total # of elements on left and right partition equal size of combined arrays
            System.out.println("midpoint: " + midpoint + " and cutoff: " + cutOff);

                /// FIRST ARRAY ///
            // Max element on left partition of the first array
            int L1 = (midpoint == 0) ? Integer.MIN_VALUE : nums1[midpoint - 1];
            // Min element on right partition of the first array
            int R1 = (midpoint == nums1.length) ? Integer.MAX_VALUE : nums1[midpoint];
                // E.g.    1 2 [4] | [6] 7      ---> L1 = 4 and R1 = 6
                //           3  5  | 10 12 14

                /// SECOND ARRAY ///
            // Max element on left partition of the second array
            int L2 = (cutOff == 0) ? Integer.MIN_VALUE : nums2[cutOff - 1];
            // Min element on left partition of the second array
            int R2 = (cutOff == nums2.length) ? Integer.MAX_VALUE : nums2[cutOff];
            // E.g.    1 2 4 | [6] 7
            //         3 [5] | [10] 12 14  ---> L2 = 5 and R2 = 10

            // Eg.   L1  |  L2   -->   1 | 2   -->  1 <= 4 (true)   -->   1 2 | X   -->    2 <= 3 (true)
            //       R1  |  R2   -->   3 | 4   -->  3 <= 2 (false)  -->     X | 3 4   -->   -Infinity <= Infinity (true)
                // Midpoint moves up to midpoint + 1 --> past 1, 2 into empty space...
                // Since no # are on the top right side (R1), replaced with infinity
                // Cutoff is now 0, so no # on the bottom left side (L2), replace with negative infinity
                // -Infinity <= Infinity, now both L1 <= R2 & L2 <= R1
                // If length of combined arrays is even, the median will be the greater # on the left partition + smallest # on right partition
                // If length is odd, median will just be the great # on the left partition

            System.out.print("-- LEFT PARTITION - L1: " + L1 + " R1: " + R1);
            System.out.print(" --- RIGHT PARTITION - L2: " + L2 + " R2: " + R2);
            System.out.println(" --");

            // Largest # in left partition of first array <= smallest # in the right partition of the second array...
            // AND largest # in left partition of second array <= smallest # in the right partition of the first array
            if (L1 <= R2 && L2 <= R1) {
                System.out.print("-- FINAL -- LEFT PARTITION - L1: " + L1 + " R1: " + R1);
                System.out.print(" --- RIGHT PARTITION - L2: " + L2 + " R2: " + R2);
                System.out.println(" --");

                // Get largest # on the left half of partition
                double maxLeft = Math.max(L1, L2);
                // Get smallest # on the right half of partition
                double minRight = Math.min(R1, R2);

                // If length of combined arrays is even
                if ((nums1.length + nums2.length) % 2 == 0) {
                    // Median is the average of the two #'s
                    return (maxLeft + minRight) / 2;
                }
                // Length of combined arrays is odd
                else {
                    // Median is the middle # of the combined arrays
                    // Since odd, one partition (the right) will have an extra element; get min element of right partition
                    return minRight;
                }
            }
            // Largest # in left partition of first array is less than smallest # in the right partition of the second array
            // But, largest # in left partition of second array is greater than smallest # in the right partition of the first array
            // E.g.  2 | 4     -->   2 <= 8 (true)
            //       7 | 8     -->   7 <= 4 (false)
            else if (L1 < R2) {
                // Shift to the right past the midpoint
                // E.g.   2 4 | X    -->   2 <= 8 (true)
                //          7 | 8    -->   7 <= Infinity (true)
                low = midpoint + 1;
                System.out.println("L1: " + L1 + " less than R2: " + R2);
                System.out.println("But L2: " + L1 + " is greater than R2: " + R2);
            }
            // Largest # in left partition of first array is greater than smallest # in the right partition of the second array
            // E.g.   8 | 9    -->   8 <= 2 (false)
            //        1 | 2    -->   1 <= 9 (true)
            else {
                // Shift to the right past the midpoint
                // E.g.    X | 8 9    -->   -Infinity <= 2 (true)
                //         1 | 2      -->   1 <= 8 (true)
                high = midpoint - 1;
                System.out.println("L1: " + L1 + " greater than R2: " + R2);
            }
        }
        return median;
    }

    public static void main(String[] args) {
        System.out.println("median is: " + search(nums1, nums2));
        System.out.println("--------------- NEXT ---------------");
        System.out.println("median is: " +  counter(nums1, nums2));
    }
}
