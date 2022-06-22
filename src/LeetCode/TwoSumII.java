package LeetCode;

import java.util.Arrays;

public class TwoSumII {

    static int[] numbers = {2, 7, 11, 15};
    static int target = 9;


    public static int[] pointers(int[] numbers, int target) {

        // Index will be in the range of 1 <= index1 < index2 <= numbers.length; index cannot be less than 1
        // Shift index by + 1
        int left = 0;
        int right = numbers.length - 1;

        for (int i = 0; i < numbers.length; i++) {

        if (numbers[right] != numbers[left]) {
            System.out.println("right: " + numbers[right]);
            System.out.println("left: " + numbers[left]);
            if (numbers[right] + numbers[left] > target ) {
                right--;
            }
            else if (numbers[right] + numbers[left] < target ) {
                left++;
            }
          }
        }
        return new int[] {left + 1, right + 1};
    }

    public static void main(String[] args) {
//        pointers(numbers, target);
        System.out.println(Arrays.toString(pointers(numbers, target)));
    }
}
