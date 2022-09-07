package LeetCode;

import java.util.HashSet;

public class LinkedListCycle {

    public static void set() {

        // Time complexity - O(N) - Going through each node in the linked list
        // Space complexity - O(N) - Adding nodes to hashset

        ListNode nodeA = new ListNode(3);
        ListNode nodeB = new ListNode(2);
        ListNode nodeC = new ListNode(0);
        ListNode nodeD = new ListNode(-4);

        // Linking next
        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeB;

        // Keeps track of all nodes visited in the linked list
        HashSet<ListNode> set = new HashSet<>();
        ListNode current = nodeA;

        while (current != null) {
            // node .next shows up again; that the linked list cycled back a node already visited
            if (set.contains(current.next)) {
                System.out.print("current node: " + current.value + " cycles back to -> " + current.next.value);
                break;
            }
           set.add(current);
            current = current.next;
        }

    }

    public static boolean fastSlow () {

        // Time complexity - O(N) - Going through each node in the linked list
        // Space complexity - O(1)

        // Fast and slow algorithm; Floyd's tortoise and hare (cycle detection) algorithm:
            // Two pointers in a linked list, one moving twice as fast (the hare) than the other (the tortoise)...
            // If they intersect, there is a cycle in the linked list, but if they don't there is no cycle.

        ListNode nodeA = new ListNode(3);
        ListNode nodeB = new ListNode(2);
        ListNode nodeC = new ListNode(0);
        ListNode nodeD = new ListNode(-4);

        // Linking next
        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeB;

        ListNode slow = nodeA;
        ListNode fast = slow;

        // fast.next != null for list with even # of nodes
        while (fast != null && fast.next != null) {

                slow = slow.next;
                // Fast pointer moves a distance of two nodes per cycle
                fast = fast.next.next;

                // Floyd's Tortoise and Hare algorithm walk-through //
                // E.g. 1 -> 2 -> 3 -> 4 -> [2]  -  List goes from 4 and cycles back to 2
                    // 1) slow = 1; fast = 1  - both pointers start at same position
                    // 2) slow = 2; fast = 3  -  slow moved 1 node up, fast moved 2 nodes up
                    // 3) slow = 3; fast = 2  -  fast moved 2 nodes up reaching 4 which cycled back to 2
                    // 4) slow = 4; fast = 4  -  fast catches up to slow

                //  The two pointers meet again at the final node that cycles back around
                if (slow == fast) {
                    return true;
                }
        }
        return false;
    }

    public static void main(String[] args) {
        set();
        System.out.print("result is: " + fastSlow());

    }
}
