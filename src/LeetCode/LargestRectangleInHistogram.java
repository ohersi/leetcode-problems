package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LargestRectangleInHistogram {

    public static int[] height = {2,1,5,6,2,3};

    // Width of each bar is 1.

    // GOAL
        // Return the area of the largest rectangle in the histogram.

    public static void bruteForce(int[] height) {

        Stack<Integer> positionStack = new Stack<>();
        Stack<Integer> heightStack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i < height.length; i++) {

            int startingIndex = i;

            // If current height is less than top of the height stack
            while (!heightStack.isEmpty() && height[i] < heightStack.peek()) {

                // Calculate the maximum area - top of stack height * distance between current block and top of stack; Height * Width
                int currentHeight =  heightStack.pop();;
                int width = i - positionStack.peek();
                maxArea = Math.max(maxArea, currentHeight * width);
//
//                // Keep track of the index of the block removed
//                // Since current height is the minimum height of rectangle...
//                    // the rectangle width can be extended back to the last block that was greater than current one
//                    // E.g. [3, 2] --> 2 can fit into 3 giving an area of 2x2 = 4
                startingIndex = positionStack.pop();
//                // Keep removing any block that is greater than current - the shorter block is the cut-off for the height of the rectangle
                System.out.println("maxArea: " + maxArea);
            }

            positionStack.push(startingIndex);
            heightStack.push(height[i]);
//            System.out.println("positionStack:" + positionStack);
//            System.out.println("heightStack:" + heightStack);
//            System.out.println("_______________");
        }

        // Any block remaining in the stack are blocks that span from their index to the end
            // [[0, 1], [2, 2], [5, 3]]

        // Calculate the area of the remaining blocks that reach the end of the histogram
        for (int i = positionStack.size(); i > 0; i--) {
            int currentHeight = heightStack.pop();
            // Distance from current block to end of the histogram
            int width = height.length - positionStack.pop();
            maxArea = Math.max(maxArea, currentHeight * width);
            System.out.println("final maxArea: " + maxArea);
        }
    }

    public static void main(String[] args) {
        bruteForce(height);
    }
}
