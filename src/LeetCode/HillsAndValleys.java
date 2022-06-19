package LeetCode;

public class HillsAndValleys {

    static int[] nums = {2,4,1,1,6,5};

    public static void countVer(int[] nums) {
        int prev = nums[0];
        int count = 0;

        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1] && nums[i] > prev ) {
                count++;
                prev = nums[i];
            }
            else if(nums[i] < nums[i+1] && nums[i] < prev) {
                count++;
                prev = nums[i];
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        countVer(nums);
    }
}
