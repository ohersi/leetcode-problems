package LeetCode;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSums {

    static int[] numbers = {2,7,11,15};
    static int target = 9;

    public static int[] twoSum(int[] numbers, int target) {

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            if (hashMap.containsKey(TwoSums.target - numbers[i])) {
//                System.out.println(hashMap.get((TwoSums.target - numbers[i])));
                return new int[] {hashMap.get((TwoSums.target - numbers[i])), i};
            }
            hashMap.put(numbers[i], i);
        }
//        System.out.println(hashMap);
//        System.out.println(Arrays.toString(test));
        return new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(numbers, target)));
    }
}
