package LeetCode;

import java.util.*;

public class DailyTemperatures {

    public static int[] temperatures = {73,74,75,71,69,72,76,73};


    public static void bruteForce(int[] temperatures) {
        // Time complexity is 0 (N ^ 2) quadratic - nested for loops
        // For each temp[i], check each other number in array until it is greater than temp[i], keeping track of distance
            //E.g. temp[3] = 75 ---> 71, 69, 72, 76! ---> temp[6] = 76 ---> distance = 4

        int[] array = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {

            boolean foundGreater = false;

            for (int j = i; j < temperatures.length; j++) {
                if (temperatures[i] < temperatures[j]) {
                    System.out.println("temp[i] " + temperatures[i] + " - temp[j]: " + temperatures[j]);
                    System.out.println("DISTANCE: " + (j - i));
                    array[i] = j - i;
                    foundGreater = true;
                    break;
                }
            }
            if (foundGreater == false) {
                array[i] = 0;
            }

            System.out.println(Arrays.toString(array));
            System.out.println("NEXT i");
        }


    }

    public static int[] stack(int[] temperatures) {

        Stack<Integer> stack = new Stack<>();
        int[] array = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {

            // Remove all temps from the top of the stack that are less than the current temp
                // E.g. Stack = [75, 71, 69] ---> temp[i] = 72 ---> 69 is removed, 71 is removed
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
//                System.out.println("removing: " + temperatures[stack.peek()] + " --- current temp: " + temperatures[i]);

                // Add the distance between current warmest day and last warmest day
                array[stack.peek()] = i - stack.peek(); // index [stack.peek()]; it is the index of the last warmest day
                stack.pop();
            }
            // Add temperatures index to stack
                stack.add(i);
//            System.out.println("adding: " + temperatures[i]);
//            System.out.println("STACK: " + stack);
        }
//        System.out.println("ARRAY: " + Arrays.toString(array));
        return array;
    }

    public static void main(String[] args) {
//        bruteForce(temperatures);
        System.out.println("Results: " + Arrays.toString(stack(temperatures)));
    }
}
