package LeetCode;

public class ReorderList {

    public static void loops(){

        // Time complexity - O (N^2) - First loop n, nested loop (window - n) -1 --> O (N * (M-N))
        // Space complexity - O(1)

        // List
        ListNode nodeA = new ListNode(1);
        ListNode nodeB = new ListNode(2);
        ListNode nodeC = new ListNode(4);
        ListNode nodeD = new ListNode(6);
        ListNode nodeE = new ListNode(10);
        // Linking
        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeE;

        // L0 → L1 → … → Ln - 1 → Ln; n = list.length - 1;
            // E.g. L0 = 1, L1 = 2, L2 = 4... L4 = 10;

        // Output: L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
            // 1 -> 10 -> 2 -> 6 -> 4
            // First node, last node, 1, second from last, 2, third from last, 3, fourth from last....


        // current - Keeps track of current element, only moves forward by 1 node after all actions are complete
        // original - Keeps track of order of the original linked list
        // Iterate through nodes until reach last node (current.next == null)
        // Second pointer will go through nodes until it reaches (n-nth) node...
        // When pointer reaches (n-nth) node, point current.next to the (n-nth) node
        // Pointer node should point back to the original current.next node of current (stored in original.next)
        // Reset current as well back to its original next node

        ListNode current = nodeA;
        ListNode original = nodeA;

        // Counter variables, keeps track of the # of nodes needed to travel to get to destination
        int window = 0;
        // # of iterations of the first while loop
        int n = 0;

        ListNode temp = nodeA;
        while (temp != null) {
            temp = temp.next;
            window += 1;
        }
        System.out.println("Initial size of the window = " + window);

        // Two loops //
        // First iterates through each node in the list once
        while (current != null) {
           // Pointer starts same place as current node, will travel n-nth places
            ListNode pointer = current;

            // Second loop goes from current node to n-nth element in list
                // E.g.   1 -> [2] -> 4 -> 6 -> 10     n = 4, current = 2 --> [4 - 2] = 2 --> going through 2 elements
                //              2 -> 4 -> [6]
            for (int j = 0; j < ((window - n) - 1); j++) {
                System.out.println("pointer at: " + pointer.value);
               pointer = pointer.next;
            }
            System.out.println("current: " + "went " + ((window - n)- 1) + " #'s ahead and " + current.value + " will point to: " + pointer.value);

            // pointer points back to the next node in the og
            pointer.next = original.next;
            // OG linked list moves forwards to the next node
            original = original.next;

            // Switch current node next address to new address (n-nth) if window is greater than 0
            if ( ((window - n) - 1) > 0) {
                current.next = pointer;
            }
            else {
                // Window is 0; list is reordered, assign the last node.next to null
                current.next = null;
                break;
            }

            // Move onto the actual next node (original list)
            current = original;


          n++;
          // Shrinking window
          window -= 1;
            System.out.println(" ----------- ");
        }


        // PRINTING REORDERED LIST //
        ListNode list = nodeA;
        while (list != null) {
            System.out.println("list node: "+ list.value);
            list = list.next;
        }
    }

    public static void pointers() {

        // Time complexity - O(N) - Traversing through the linked list, where n equals number of nodes in the list
        // Space complexity - O(1)

        // List
        ListNode nodeA = new ListNode(1);
        ListNode nodeB = new ListNode(2);
        ListNode nodeC = new ListNode(4);
        ListNode nodeD = new ListNode(6);
        ListNode nodeE = new ListNode(10);
        // Linking
        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeE;

        // Split the linked list into two halves
        // Take one node from each half until reach midpoint; for the first half go forwards and second half backwards
            // E.g.  1 -> 2 -> 4  |  6 <- 10
            //    1st -  [1] -> 2 -> 4  -> |  6 <- [10]     1 -> 10
            //    2nd -  1 -> [2] -> 4  - >| [6] <- 10      1 -> 10 -> 2 -> 6
            //    3rd -  1 -> 2 -> [4]  -> | < - 6 <- 10    1 -> 10 -> 2 -> 6 -> 4 -> [6] (nodes now form a loop, no exit)
        // Since middle node is the last node in the reordered list, it should point to null
            //    FINAL -  1 -> 2 -> [4]  -> NULL | <- 6 <- 10    1 -> 10 -> 2 -> 6 -> 4 -> NULL

       /////////// Determine the middle node of the linked list ///////////

        ListNode midNode = nodeA;
        ListNode fast = nodeA.next;

        // fast.next != null for list with even # of nodes
        while (fast != null && fast.next != null) {
            midNode = midNode.next;
            fast = fast.next.next;
        }
        System.out.println("midNode: " + midNode.value);


      /////////// Reverse the second half of the linked list ///////////

        ListNode prev = midNode;
        ListNode current = prev.next;

        while (current != null) {

            // Holds original next node
            ListNode temp = current.next;

            // Point backwards
            current.next = prev;

            System.out.println("current: " + current.value + " points to: " + prev.value);

            // Move prev pointer up to current pointer
            prev = current;

            // Move forwards to the original next node
            current = temp;
        }

        // Second half reversed, ending at middle node which current points to next node in the original list; causes loop...
            // E.g. 1 -> 2 -> 4 <-> 6 <- 10 - 6 points to 4, and 4 points to 6... chain is not broken
        // Since middle node is the last node in the reordered list; next node will be null
        midNode.next = null;

        /////////// Reorder list ///////////

        ListNode start = nodeA;
        // prev ended at the last node in the linked list
        ListNode end = prev;

        // Holds temporary og next nodes
        ListNode tempStart = start;
        ListNode tempEnd = end;

        while (start != null && end != null) {

            // Move the og nodes forward to the next node
            tempStart = tempStart.next;
            tempEnd = tempEnd.next;

            // start.next -> end.next -> tempStart (og .next of start)
            start.next = end;
            end.next = tempStart;

            // Reset both pointers
            start = tempStart;
            end = tempEnd;
        }

      ///////////  PRINTING REORDERED LIST ///////////

        ListNode list = nodeA;
        while (list != null) {
            System.out.println("node: " + list.value);
            list = list.next;
        }

    }

    public static void main(String[] args) {
//        loops();
        pointers();
    }
}
