package LeetCode;

import java.util.Stack;

public class MinStack {

    private Stack<Integer> stack;

    // This stack keeps track of the minimum element of the original stack
    private Stack<Integer> minElementsStack;

   public MinStack() {
       stack = new Stack<>();
       minElementsStack = new Stack<>();
   }

    public void push(int val) {
        stack.push(val);

        // If minElementStack is empty, it also means the original stack is empty as well; val can be inserted for both
        if (minElementsStack.isEmpty()) {
            minElementsStack.push(val);
        }
        // Keep track of the minimum element; compare current val with the top of the minElementStack
            // E.g. val = 3 --> minElem.peek() = 1 ---> minValue will be 1
        else {
            int minValue = Math.min(val, minElementsStack.peek());
            // Add the min to the min stack
            minElementsStack.push(minValue);
        }
    }

    public void pop() {
       // Remove the top for both stacks
        stack.pop();
        minElementsStack.pop();
    }

    public int top() {
       // Return the top of the original stack
        return stack.peek();
    }

    public int getMin() {
       // return the top of the minimumElem stack
        return minElementsStack.peek();
    }


    public static void main(String[] args) {

       // Initialize object
        MinStack obj = new MinStack();

        // Adding values
        obj.push(-2);
        obj.push(0);
        obj.push(-3);

        // stack = [-3, 0, -2]
        // minElementStack = [-3, -2, -2]

        System.out.println("getMin(): " + obj.getMin()); // -3
        obj.pop();

        // stack = [0, -2]
        // minElementStack = [-2, -2]

        System.out.println("top(): " + obj.top()); // 0
        System.out.println("getMin(): " + obj.getMin()); // -2
    }
}
