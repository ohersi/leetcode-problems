package LeetCode;

public class ListNode {
    int value;
    // Points to the next node in the list
    ListNode next;
    // Points to the previous node in the list
//        ListNode prev;

    ListNode() {}

    ListNode (int value) {
        this.value = value;
    }

    ListNode (int value, ListNode next) {
        this.value = value;
        this.next = next;
    }
}
