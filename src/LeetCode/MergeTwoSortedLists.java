package LeetCode;

import java.util.LinkedList;

public class MergeTwoSortedLists {

    public static void list() {

        // First list
        ListNode nodeA = new ListNode(1);
        ListNode nodeB = new ListNode(2);
        ListNode nodeC = new ListNode(4);
        ListNode nodeD = new ListNode(5);
        ListNode nodeE = new ListNode(7);
        // Linking nodes
        nodeA.next = nodeB;
        nodeB.next= nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeE;

        // Second list
        ListNode nodeX = new ListNode(1);
        ListNode nodeY = new ListNode(3);
        ListNode nodeZ = new ListNode(4);
        // Linking nodes
        nodeX.next = nodeY;
        nodeY.next= nodeZ;


        // Pointer for first linked list
        ListNode firstPointer = nodeA;
        // Pointer for second linked list
        ListNode secondPointer = nodeX;
        // Temporary node used as the head of the new list
        // Dummy node used as a pointer to the actual first node in the new linked list
        ListNode dummy = new ListNode();
        // Pointer for the new linked list
        ListNode current = dummy;
        
        while (firstPointer != null && secondPointer != null) {

            System.out.println(" old current: " + current.value);
            System.out.println("firstPointer - " + firstPointer.value + "  or  " +  + secondPointer.value + " - secondPointer");

            if (firstPointer.value <= secondPointer.value) {
                // first pointer is smaller, append it to the current node
                current.next = firstPointer;
                // Move pointer to the next node in the first linked list
                firstPointer = firstPointer.next;
            }
            else {
                // second pointer is smaller
                current.next = secondPointer;
                // Move pointer to the next node in the first linked list
                secondPointer = secondPointer.next;
            }

            // Move to next node in the new linked list; E.g. DUMMY -> 1 -> 1 -> [2] ...
            current = current.next;
            System.out.println("smaller # is now current: " + current.value);
            System.out.println("_________________");
        }

        // Broke out of the while loop, reached the end of one of the linked lists
        // First list still has more nodes, point the current node to the node in the first list (each list is already sorted)
            // E.g.     1 -> 2 -> 4 -> 6 -> 9 -> NULL  -- current = 5, since no node after, point to next node in first array
            //          1 -> 3 -> [5] -> NULL
        if (firstPointer != null) {
            System.out.println("continuing with the firstPointer: " + firstPointer.value);
            current.next = firstPointer;
        }
        // Second list still has more nodes, point the current node to the node in the second list
        else if (secondPointer != null) {
            System.out.println("continuing with the secondPointer: " + secondPointer.value);
            current.next = secondPointer;
        }

        //////// Printing merged linked list nodes ////////////
        ListNode complete = dummy;
        while (complete.next != null) {
            System.out.println("merged list node: " + complete.next.value);
            complete = complete.next;
        }
    }

    public static void main(String[] args) {
        list();
    }
}
