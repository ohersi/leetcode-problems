package LeetCode;

public class Search2DMatrix {

    public static int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
    public static int target = 3;

    public static boolean search(int[][] matrix, int target) {

        // Index of the sub-array that will be searched using binary search
        int index = 0;

        // Check the first and last number of each sub-array
       for (int i = 0; i < matrix.length; i++) {

           int end = matrix[i].length - 1;
           // If the target is between the two points, the sub-array with the potential target number was found
           if (target >= matrix[i][0] && target <= matrix[i][end]) {
               index = i;
           }
       }

        // BINARY SEARCH

        int low = 0;
        int high = matrix[index].length - 1;

        while (low <= high) {

            int midpoint = low + (high - low) / 2;

            if (target == matrix[index][midpoint]) {
                return true;
            }

            if (target > matrix[index][midpoint]) {
                low = midpoint + 1;
            }
            else if (target < matrix[index][midpoint]) {
                high = midpoint - 1;
            }
//            System.out.println("low: " + matrix[index][low]);
//            System.out.println("midpoint: " + matrix[index][midpoint]);
//            System.out.println("high: " + matrix[index][high]);
        }
        // Target was not found.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Its: " + search(matrix, target));
    }
}
