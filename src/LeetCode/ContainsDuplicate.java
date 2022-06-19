package LeetCode;

import java.util.Arrays;
import java.util.HashSet;

public class ContainsDuplicate {

    static int[] nums = {1, 2, 3, 4, 9, 6, 8, 7, 2};

    public static boolean sortVer(int[] nums) {
    Arrays.sort(nums);
//        Set<Integer> numbers = new HashSet<>();
        for (int i = 1; i < nums.length; i++) {
          if (nums[i] == nums[i-1]) {
              return true;
          }
        }
        System.out.println(Arrays.toString(nums));
        return false;
    }

    public static boolean hashSetVer(int[] nums) {
        if (nums.length <= 1) {
            return false;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(sortVer(nums));
        System.out.println(hashSetVer(nums));
    }
}
