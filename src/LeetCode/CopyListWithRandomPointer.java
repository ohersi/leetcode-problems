package LeetCode;

import java.util.HashMap;

public class CopyListWithRandomPointer {

    public static void list() {

        // Time complexity - O(2N) -> O(N) - linear time; two loops going n + n, n being the # of nodes
        // Space complexity - O(N) - memory allocated for the hashmap; size of hashmap proportional to # of nodes

        ListNode nodeA = new ListNode(7);
        ListNode nodeB = new ListNode(13);
        ListNode nodeC = new ListNode(11);
        ListNode nodeD = new ListNode(10);
        ListNode nodeE = new ListNode(1);

        // Linking next
        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeE;

        // Linking random
        nodeA.random = null;
        nodeB.random = nodeA;
        nodeC.random = nodeE;
        nodeD.random = nodeC;
        nodeE.random = nodeA;


        // Hashmap store the original node and its clone
        // Since copy of the original nodes do not point anywhere, use a hashmap to reference the og .next and .random
        HashMap<ListNode, ListNode> map = new HashMap<>();

        ListNode dummy = new ListNode();
        dummy.next = nodeA;
        ListNode current = dummy.next;

        // Linking the nodes with its corresponding clone node in hashmap
        while (current != null) {

            // Create clone nodes and give it the value of current original node
            ListNode cloneNode = new ListNode();
            cloneNode.value = current.value;

            // Insert the newly created node and its original current node into hashmap
            map.put(current, cloneNode);

            current = current.next;
        }

        // Link the clone nodes to their respective neighbors
        current = nodeA;

        while (current != null) {
            // Clone nodes are stored in as values of the og nodes...
            // Get the clone nodes, and set their .next and .random to the OG .next and .random nodes
            map.get(current).next = map.get(current.next);
            map.get(current).random = map.get(current.random);

            current = current.next;
        }

        /////// PRINT OUT CLONE NODES ///////

        for (ListNode entry : map.keySet()) {
            if (entry.next != null || map.get(entry).next != null) {
                System.out.println("OG (key) node: " + entry.value + " points to: " + entry.next.value);
                System.out.println("clone (value) node: " + entry.value + " points to: " + entry.next.value);
            }
            else {
                System.out.println("OG (key) node: " + entry.value + " points to null");
                System.out.println("clone (value) node: " + map.get(entry).value + " points to null");
            }
            ////////////////////
            if (entry.random !=null || map.get(entry).random != null) {
                System.out.println("OG (key) node: " + entry.value + " randomly points to: " + entry.random.value);
                System.out.println("clone (value) node: " + map.get(entry).value + " randomly points to: " + map.get(entry).random.value);
            }
            else {
                System.out.println("OG (key) node: " + entry.value + " randomly points to null");
                System.out.println("clone (value) node: " + map.get(entry).value + " randomly points to null");

            }
            System.out.println("_______________");
        }
    }

    public static void main(String[] args) {
        list();
    }
}
