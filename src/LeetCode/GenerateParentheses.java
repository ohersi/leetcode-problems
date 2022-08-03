package LeetCode;

import java.util.ArrayList;
import java.util.Stack;

public class GenerateParentheses {

    // BACKTRACKING RECURSION ALGORITHM
        // "The term backtracking suggests that if the current solution is not suitable, then backtrack and try other solutions.
            //Thus, recursion is used in this approach."

    // CONSTRAINTS
    // 3 opening '(' and 3 closing ')' = 6 total parentheses ---> 3 x 2 = 6
    // Keep track of number of open and closed parentheses ---> open = 0 closed = 0; after each parenthesis placed is +1
    // Check if opening value is less than input, if so '(' can be placed
    // Check if closing value is less than opening, if so ')' can be placed
    // E.g. (() ')' <--- Can be placed, close is less than open
    // E.g. () ')' <--- Cannot be placed; current number of open is 1, close is 1; therefore there is a missing opening parentheses
    public static int input = 3; // input * 2 = number of parentheses for complete parentheses. E.g. 3 * 2 = 6

    // Holds all input * 2 complete parentheses. E.g. input * 2 = 6 total parentheses
    static Stack<String> stack = new Stack<>();

    // All combination of complete parentheses
    static ArrayList<String> result = new ArrayList<>();
    static int open = 0; // once open equals input, all opening parentheses have been added
    static int close = 0; // once close equals input, all closing parentheses have been added

    public static void backtrack(int open, int close){
            // GOAL
            // If both open and close values equal input, join all strings in stack & add to result array
            if (open == close && open == input && close == input) {
                result.add(String.join("", stack));
                // Complete parentheses has been added, exit
                return;
            }
            // CHOICES - exploring choice using recursion, if decision does not work, backtrack and undo. Make another choice
            // If open is less than input, add opening parentheses '(' to stack
            if (open < input) {
                stack.push("(");
                System.out.println("adding '(' ");
                // Recursively call function, updating open by 1
                backtrack(open + 1, close);
                // Remove parentheses that was added to the stack
                System.out.println("popping:" + stack.peek());
                stack.pop();
            }
        // If close is less than input, add closing parentheses ')' to stack
            if (close < open) {
                stack.push(")");
                System.out.println("adding ')' ");
                // Recursively call function, updating close by 1
                backtrack(open, close + 1);
                // Remove parentheses that was added to the stack
                System.out.println("popping:" + stack.peek());
                stack.pop();
            }
            System.out.println("open: " + open + " --- close: " + close);
            System.out.println("STACK: " + stack);
    }

    public static void main(String[] args) {
        backtrack(open, close);
        System.out.println("result: " + result);
    }
}
