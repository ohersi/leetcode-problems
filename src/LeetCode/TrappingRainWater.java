package LeetCode;

public class TrappingRainWater {

    public static int[] height = {0,1,0,2,1,0,1,3,2,1,2,1}; // {9, 8 ,2, 6};

    public static void pointers(int[] height) {
        int left_pointer = 0;
        int right_pointer = height.length - 1;

        int max_LeftValue = height[left_pointer];
        int max_RightValue = height[right_pointer];

        int units = 0;

            while (left_pointer < right_pointer) {
                System.out.println("height[left_pointer]: " + height[left_pointer] + " and " + "height[right_pointer]: " + height[right_pointer]);

                // Checking for the two highest points for either side of the container
                //  the smaller endpoint determines the max height of water held - anything above it will overflow
                        // E.g. left = 2  right = 4 ---> 2             |
//                                                                     |
//                                                          |x x x x x |
//                                                          |x x x x x |
                if (max_LeftValue < max_RightValue) {
                    // Shift minimum endpoint by 1
                    left_pointer += 1;
                    // Compare current left height with max left height
                    max_LeftValue = Math.max(max_LeftValue, height[left_pointer]);
                    System.out.println("max_Left is now: " + max_LeftValue);
                    // Unit is the amount of water (1x1 block) that can be held
                    // E.g. If max left was the one side of a bucket and current height was the floor...
                    // The amount of water that could be held would be the difference between the two
                        // Left peak of the container = 5; current height = 2 ---> 5 - 2 = 3 units can be held
//                    System.out.println("max_LeftValue " + max_LeftValue + " - " + "height[left_pointer]: " + height[left_pointer] + "  =  " + (max_LeftValue - height[left_pointer]));
                    units += max_LeftValue - height[left_pointer];
                    System.out.println("units inside if: " + units);
                }
                else {
                    // Shift minimum endpoint by 1
                    right_pointer -= 1;
                    // Compare current right height with max right height
                    max_RightValue = Math.max(max_RightValue, height[right_pointer]);
//                    System.out.println("max_Right is now: " + max_RightValue);
//                    System.out.println("max_RightValue: " + max_RightValue + " - " + "height[right_pointer]: " + " =  " + (max_RightValue - height[right_pointer]));
                    units += max_RightValue - height[right_pointer];
                    System.out.println("units inside else: " + units);
                }
                System.out.println("______________________________");
            }
        System.out.println(units);
    }

    public static void main(String[] args) {
        pointers(height);
    }
}
