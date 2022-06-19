package LeetCode;

import java.util.Arrays;

public class ProductsOfArrayExceptSelf {

    static int[] nums = {4,5,1,8, 2};

    public static void linearTime(int[] nums) {

        int length = nums.length;

        int[] prevProducts = new int[length];
        int[] nextProducts = new int[length];
        int[] output = new int[length];
        //  4 is the first element in the array, since there is nothing to the left of it the value will be 1
        prevProducts[0] = 1;
        //  2 is the last element in the array, since there is nothing to the right of it the value will be 1
        nextProducts[length - 1] = 1;


        // Start at index[1] since there is an element before it; index[0] was already assigned 1;
        for (int i = 1; i < nums.length; i++) {
            // Loop through array, inserting the multiplication of previous nums[i] and the prevProducts[i]; everything before i.
            // E.g. prevProduct[1] = nums[1-1]  * prevProducts[1-1] ---> nums[0] * prevProducts[0]
            // prevProduct[1] = 4 * 1 ---> 4
            // FULL SEQUENCE
            // [1, 4, 0, 0, 0]
            // [1, 4, 20, 0, 0]
            // [1, 4, 20, 20, 0]
            // [1, 4, 20, 20, 160]
            prevProducts[i] = nums[i - 1] * prevProducts[i - 1];
//            System.out.println(Arrays.toString(prevProducts));
        }

        // Start at second to last index of the nums array, nextProducts[nums.length - 1] aka last index was already assigned 1 since nothing comes after it.
        // Its i >= 0 because we want to check the last index aka index[0]
        for (int i = nums.length - 2; i >= 0; i--) {
            // Loop through array, inserting the multiplication of the next nums[i] and the next nextProducts[i]; everything after i.
            // E.g. i = nums.length - 2 ---> (5 - 2 = 3) ---> i = 3;
            // nextProducts[3] = nums[3 + 1] * nextProducts[3 + 1] ---> nums[4] * nextProducts[4]
            // nextProducts[3] = 2 * 1 ---> 2
            // FULL SEQUENCE
            // [0, 0, 0, 2, 1]
            // [0, 0, 16, 2, 1]
            // [0, 16, 16, 2, 1]
            // [80, 16, 16, 2, 1]
            nextProducts[i] = nums[i + 1] * nextProducts[i + 1];
//            System.out.println(Arrays.toString(nextProducts));
        }
        // Looping through both nextProducts[i] and prevProducts[i] and multiplying them
        for (int i = 0; i < length; i++) {
            output[i] = prevProducts[i] * nextProducts[i];
        }
//    System.out.println(Arrays.toString(prevProducts));
//        System.out.println(Arrays.toString(nextProducts));
        System.out.println(Arrays.toString(output));
    }

    public static void constantSpaceTime(int[] nums) {

        // This algorithm time complexity is O(N), N being the length of nums arrays

        // Using only one array, the output, to save space, instead of 3.
        // output array now holds the values both the prevProducts and nextProducts arrays.
        int[] output = new int[nums.length];
        output[0] = 1;
        // X variable is the product of all next elements; it is the same as nextProducts
        int x = 1;

        // Start at index[1] since there is an element before it; index[0] was already assigned 1;
        for (int i = 1; i < nums.length; i++) {
            // Loop through array, inserting the multiplication of previous nums[i] and the output[i]; everything before i.
            // E.g. output[1] = nums[1-1]  * output[1-1] ---> nums[0] * output[0]
            // output[1] = 4 * 1 ---> 4
            // FULL SEQUENCE
            // [1, 4, 0, 0, 0]
            // [1, 4, 20, 0, 0]
            // [1, 4, 20, 20, 0]
            // [1, 4, 20, 20, 160]
            output[i] = nums[i - 1] * output[i - 1];
//            System.out.println(Arrays.toString(output));
        }

        // Start at last index of the nums array, output[nums.length - 1] since were multiplying the last index by x.
        // Its i >= 0 because we want to check the last index aka index[0]
        for (int i = nums.length - 1; i >= 0; i--) {
            // Loop through array, inserting the multiplication of the next nums[i] and the next nextProducts[i]; everything after i.
            // E.g. i = nums.length - 1 ---> (5 - 1 = 4) ---> i = 4;
            // output[4] = output[4] * x ---> 160 * 1 ---> 160
            // x = x * nums[4] ---> 1 * 2 ---> {2}

            // output[3] = output[3] * x ---> 20 * 2 ---> 40
            // x = x * nums[3] ---> 2 * 8 ---> {16}

            // output[2] = output[2] * x ---> 20 * 16 ---> 320
            // x = x * nums[2] ---> 16 * 1 ---> {16}

            // output[1] = output[1] * x ---> 4 * 16 ---> 64
            // x = x * nums[1] ---> 16 * 5 ---> {80}

            // output[0] = output[0] * x ---> 1 * 80 ---> 80
            // x = x * nums[0] ---> 80 * 4 ---> {320} ---> array size has already been reached so this number is irrelevant
            output[i] = output[i] * x;
            x =  x * nums[i];
//            System.out.println(Arrays.toString(output));
        }
        System.out.println(Arrays.toString(output));
    }

    public static void main(String[] args) {
//        linearTime(nums);
        constantSpaceTime(nums);
    }
}
