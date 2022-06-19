package BroCode;

import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {

        //        LIFO // Last In - First Out
        //        stores objects into a sort of 'vertical tower'
        //        push() to add to the top
        //        pop( to remove from the top

        Stack<String> stack = new Stack<String>();
//        System.out.println(stack.empty());

        stack.push("Elden Ring");
        stack.push("Disco Elysium");
        stack.push("Persona 5");
        stack.push("Breath of the Wild");

//        pop the top most object, in this example the last in was Breath of the Wild, so it would be the first out
        stack.pop();
//        Look at top of stack
        System.out.println(stack.peek());
//        Search for object inside of stack
        System.out.println(stack.search("Disco Elysium"));
        System.out.println(stack);

//        uses of stack?
//        1. undo/redo features in text editors
//        2. moving back/forwards through browser history
//        3. backtracking algorithms (maze, file directories)
//        4. calling functions (call stack)

    }
}
