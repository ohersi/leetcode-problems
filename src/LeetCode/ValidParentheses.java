package LeetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {

    public static String s = "()[]{}";

    public static boolean bruteForce(String s){

        Stack<Character> stack = new Stack<>();
        // Closing parentheses as keys because they indicate a complete bracket
            // Value is opening parentheses
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');

        // Parentheses are added to the stack, once closed parentheses is found
        // Check for opening parentheses at the top of the stack
        // If found, the parentheses form a complete bracket, so they can be removed from the stack
        // If stack is empty after loop, then the condition for all valid parentheses (complete brackets) is true
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                System.out.println("key is: " + s.charAt(i) + " and value is: " + map.get(s.charAt(i)));
                System.out.println("checking top of stack: " + stack.peek());
                // Check if stack is not empty and if char at the top of the stack corresponding value in the hashmap
                    // E.g. ')' value is '(' in hashmap ---> top of stack: '(' ---> true so pop from stack
                if (!stack.isEmpty() && stack.peek() == map.get(s.charAt(i))) {
                    System.out.println("match! pop top element from the stack");
                    stack.pop();
                }
                // Current char hashmap value is not at the top of the stack; parentheses do not match
                else {
                    return false;
                }
            }
            // Since the map checks for closing parentheses, as that will close the brackets...
            // opening parentheses can continue to be added to the stack
            else {
                stack.add(s.charAt(i));
            }
            System.out.println("________________");
        }

        if (stack.isEmpty()) {
            return true;
        }
        else return false;
    }

    public static void main(String[] args) {
        System.out.print(bruteForce(s));
    }
}
