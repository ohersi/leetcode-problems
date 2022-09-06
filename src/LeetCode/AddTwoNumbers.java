package LeetCode;

public class AddTwoNumbers {

    public static void list() {

        // Time complexity - O(N + M) -> O(N) - Going through each linked list once
        // Space complexity- O(N) - Creating a new linked list from addition

        // List 1
        ListNode nodeA = new ListNode(2);
        ListNode nodeB = new ListNode(4);
        ListNode nodeC = new ListNode(9);

        // List 2
        ListNode nodeW = new ListNode(5);
        ListNode nodeX = new ListNode(6);
        ListNode nodeY = new ListNode(4);
        ListNode nodeZ = new ListNode(9);


        // Linking next
        nodeA.next = nodeB;
        nodeB.next = nodeC;

        nodeW.next = nodeX;
        nodeX.next = nodeY;
        nodeY.next = nodeZ;

        ListNode l1Current = nodeA;
        ListNode l2Current = nodeW;

        ListNode head = new ListNode();
        // Holds current node of new linked list
        ListNode current = head;

        // If both numbers combined is greater than 9; node holds only single digit number
        int remainder = 0;

        while (l1Current != null || l2Current != null || remainder != 0) {

            // Holds addition of current two numbers and the remainder
            int total = 0;

            // If first linked list is not null add it to the total
            if (l1Current != null) {
                total +=  l1Current.value;
                // Go forwards to the next node in the first list
                l1Current = l1Current.next;
            }

            // If second linked list is not null add it to the total
            if (l2Current != null) {
                total +=  l2Current.value;
                // Go forwards to the next node in the second list
                l2Current = l2Current.next;
            }

            // Finally add the remainder; hold over from previous addition
            total += remainder;

            // Create new node with value of current addition; single-digit
                // E.g. 9 + 4 = 13 ---> new node = 3, remainder = 1
            ListNode node = new ListNode(total % 10);

            // Update remainder of current addition is a double-digit; total greater than 9
            remainder = total / 10;

            // Point current node of the new linked list to the newly created node
            current.next = node;
            // Move new linked list forwards to newly created node;
            current = current.next;
        }

        ////// PRINTING NEW NODES //////
        ListNode print = head;

        while (print != null) {
            if (print.next != null) {
                System.out.println("node: " + print.value + " points to: " + print.next.value);
            }
            else {
                System.out.println("node: " + print.value + " points to null");
            }
            print = print.next;
        }
    }

    public static void main(String[] args) {
        list();
    }
}
