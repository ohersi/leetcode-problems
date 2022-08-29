package LeetCode;

public class RemoveNthNodeFromEndOfList {

    public static void list() {
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

    public static void main(String[] args) {
        list();
    }
}
