package LeetCode;

public class ContainerWIthMostWater {

    public static int[] height = {1,8,6,2,5,4,8,3,7};

    public static void bruteForce(int[] height)  {
        // The time complexity of this algorithm is O(N^2)
        int area;
        int largestContainer = 0;

        for (int i = 0; i < height.length; i++) {
            // Check every index against all the other indices
            for (int j = i + 1; j < height.length; j++) {
                // Distances between two indices - width of the container
                int width;
                width = j - i;
                //  Math.min() will compare two endpoints and return the smaller one
                //  the smaller endpoint determines the height of the container - anything above it will overflow
                area = width * Math.min(height[i], height[j]);
//                System.out.println("area: " + area);
                largestContainer = Math.max(largestContainer, area);
//                System.out.println("largestContainer: " + largestContainer);
            }
//            System.out.println("______________");
            }
        System.out.println(largestContainer);
        }

     public static void pointers(int[] height) {
        // Looking for largest area ---> largest area = Maximum height * maximum width
        int start = 0;
        int end = height.length - 1;

         int area;
         int largestContainer = 0;
        // Compare two pointers, start outward to inward
         while (start < end) {
             // Distances between two indices - width of the container
             int width;
             width = end - start;

             //  Math.min() will compare two endpoints and return the smaller one
             //  the smaller endpoint determines the height of the container - anything above it will overflow
             area = width * Math.min(height[start], height[end]);
             largestContainer = Math.max(largestContainer, area);
             // Shifting pointers so that the larger pointer is the potential max height of the container
             // When compared to other pointer, the smaller pointer will be the maximum height of the container
             // Smaller pointer height * width will be the area of container
             if (height[start] < height[end]) {
                 start++;
             }
             else {
                 end--;
             }
         }
         System.out.println(largestContainer);
     }

    public static void main(String[] args) {
//        bruteForce(height);
        pointers(height);
    }
}
