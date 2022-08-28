package LeetCode;

public class ReverseLinkedList {


    // Singly Linked List node POINTS to the next node on the list, going one direction only...
    // Singly Linked List node only has address to the next node, it does not know the address to the node before it
        // E.g.   NULL -> 3 -> 5 -> 4 -> 2 -> NULL  -------  head (3) points to 5 which points to 4...
    // Doubly-Linked List can point to the next AND previous address
        // E.g.   NULL <-> 3 <-> 5 <-> 4 <-> 2 <-> NULL  -------  head (3) points next to 5 and prev to NULL...

    public static ListNode linked() {

        ListNode nodeA = new ListNode(3);
        ListNode nodeB = new ListNode(5);
        ListNode nodeC = new ListNode(4);
        ListNode nodeD = new ListNode(2);

        // pointers
        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;

//        // Counting # of nodes in the linked list from head to null
//        ListNode current = nodeA;
//        int count = 1;
//
//        while (current.next != null) {
//            System.out.println("current node: " + current.value);
//            current = current.next;
//            count++;
//        }
//        System.out.println("count: " + count);

        ListNode current = nodeA;
        ListNode prev = null;

        while (current != null) {

         System.out.println("current: " + current.value);

        // Temporary save the address of the nex node; E.g. 1 -> 2 -> [3] - next node of 2 is 3
        ListNode originalNext;
        originalNext = current.next;

        // Current node points to the previous address; E.g. [1] <- 2 ... 3 - next node of 2 is now pointing back to 1, breaking the link between 2 and 3
            current.next = prev;

        // Previous pointer moves to current
            prev = current;
//            System.out.println("prev: " + prev.value);

        //  Current node moves changes to the original next node using the temporary address
            // E.g.   NULL <- 2 <- [3] -> 4 -> NULL
                //  current = 3, prev = 2, next = 4, originalNext = 4  ---> current = 3, prev = 2, next = 2, originalNext = 4
            current = originalNext;
        }

        // Since current reaches past the last node and becomes null, the last node is stored in prev
        return prev;
    }

    public static void main(String[] args) {
        System.out.println("Last node value is: " + linked().value);
    }
}
