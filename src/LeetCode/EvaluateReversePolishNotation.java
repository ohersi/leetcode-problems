package LeetCode;

import java.util.Stack;

public class EvaluateReversePolishNotation {

    public static String[] token = {"3","11","+","5","-"}; // =  9 // "3","11","5","+","-" = -13

    public static int stack(String[] token) {

        Stack<Integer> stack = new Stack<>();
        int result;

        for (String string : token) {

            // If an arithmetic operator is encountered, remove the top two elements from the stack
                // 2nd from top (operator) 1st from top = result
                if (string.equals("+")) {
                        int firstFromTop = stack.pop();
                        int secondFromTop = stack.pop();
                        // Add the result to the top of the stack
                        stack.push(secondFromTop + firstFromTop);
                        System.out.println("second + first: " + stack.peek());
                    }
                else if (string.equals("-")) {
                        int firstFromTop = stack.pop();
                        int secondFromTop = stack.pop();
                        // Add the result to the top of the stack
                        stack.push(secondFromTop - firstFromTop);
                        System.out.println("second " + secondFromTop + " minus first: " + firstFromTop + " = " + stack.peek());
                    }
                else if (string.equals("*")){
                        int firstFromTop = stack.pop();
                        int secondFromTop = stack.pop();
                        // Add the result to the top of the stack
                        stack.push(secondFromTop * firstFromTop);
                        System.out.println("second * first: " + stack.peek());
                    }
                else if (string.equals("/")){
                        int firstFromTop = stack.pop();
                        int secondFromTop = stack.pop();
                        // Add the result to the top of the stack
                        stack.push(secondFromTop / firstFromTop);
                        System.out.println("second / first: " + stack.peek());
                    }
                else {
                    // Convert string number into integer, add to top of stack
                    stack.push(Integer.valueOf(string));
                }
            System.out.println(" adding to the stack: " + string + " ---> STACK: " + stack);
            System.out.println("_____________");
        }
        // Final result is in the top of the stack
        result = stack.peek();
        return result;
    }

    public static void main(String[] args) {
        System.out.println("result is: " + stack(token));
    }

}
