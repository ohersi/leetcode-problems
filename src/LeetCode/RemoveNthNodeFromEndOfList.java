package LeetCode;

public class RemoveNthNodeFromEndOfList {

    public static void list() {

        // Time complexity - O(N + (count - N)) -> O(N) - Traversing list once to get size, and again up until nth node from end
        // Space complexity - O(1)

        // List
        ListNode nodeA = new ListNode(1);
        ListNode nodeB = new ListNode(2);
        ListNode nodeC = new ListNode(3);
        ListNode nodeD = new ListNode(4);
        ListNode nodeE = new ListNode(5);
        // Linking
        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeE;

        // Go through list once reach end, keep count of total nodes in list
        // Find nth node from end ---> ( count - n )
        // Two pointers, current and previous
        // Go through list again, when reach (count - n ) set prev node to current.next

        int n = 2;

        ListNode dummy = new ListNode(0);
        dummy.next = nodeA;


        //// Get number of nodes in the linked list /////

        ListNode temp = dummy;
        int count = 0;

        while (temp.next != null) {
            temp = temp.next;
            count += 1;
        }
        System.out.println("# of nodes: " + count);

        if (count <= 1) {
            System.out.println("only 1 node, delete it");
        }

        //// Removing the nth node ////

        ListNode current = dummy;
        // Keeps track of the node before current
        ListNode prev;
        // Check track of the ith node in the list, when it reaches nth node, remove it
        int i = 0;

        while (current != null) {

            prev = current;
            current = current.next;

            // Reached the nth node, point the previous node past the nth node skipping it
                // E.g. n = 2; removing the 2 to last node
                    // 1 -> 3 -> 5 ---->  1 -> [3] -> 5 ---->  1 -> 5
            if (i == count - n) {
                prev.next = current.next;
                current.next = null;
                break;
            }

            i++;
        }

        ///// PRINTING OUT LIST /////

        ListNode list = nodeA;
        while (list != null) {
            System.out.println("node: " + list.value);
            list = list.next;
        }

    }

    public static void singlePass() {

        // Time complexity - O(N) - Single pass through linked list
        // Space complexity - O(1)

        // List
        ListNode nodeA = new ListNode(1);
        ListNode nodeB = new ListNode(2);
        ListNode nodeC = new ListNode(3);
        ListNode nodeD = new ListNode(4);
        ListNode nodeE = new ListNode(5);
        // Linking
        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeE;

        int n = 2;

        ListNode dummy = new ListNode();
        dummy.next = nodeA;

        ListNode slow = dummy;
        ListNode fast = dummy;

        // Traverse the list until fast reaches n nodes from start
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        // Fast continues until the end
        // Slow traverses the list until it reaches node before nth node from end (sizeOfList - n)
        while (fast !=null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Slow will reach the node that fast ended on after the for loop
        // The nth node will be the node after slow; slow.next
        // Remove it by pointing slow.next past nth node to slow.next.next
            // E.g.  n = 2
            //     1 -> [2] -> 3 -> 4  ---> slow = 2 --->  1 -> 2 -> 4; connected 2 to 4, removing 3
        slow.next = slow.next.next;


        ///// PRINTING OUT LIST /////

        ListNode list = nodeA;
        while (list != null) {
            System.out.println("node: " + list.value);
            list = list.next;
        }
    }

    public static void main(String[] args) {
//        list();
        singlePass();
    }
}
