package class03;

import genericmethods.ListElements.*;

public class C01_ReverseList {

    /**
     * 反转单链表、反转双链表
     */

    /**
     * 这个方法是依次把下一个挪到头上来
     */
    public static Node reverseLinkedList1(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        while (head.next != null) {
            next = head.next;
            head.next = next.next;
            next.next = cur;
            cur = next;
        }
        return cur;
    }

    /**
     * 这个方法是造一个新的链表，每次都把之前的头拽下来，放到新的头上
     */
    public static Node reverseLinkedList2(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = null;
        Node node4 = new Node(1);
        Node node5 = new Node(2);
        Node node6 = new Node(3);
        node4.next = node5;
        node5.next = node6;
        node6.next = null;
        Node res1 = reverseLinkedList1(node1);
        Node res2 = reverseLinkedList2(node4);
        while (res1 != null && res2 != null) {
            if (res1.val != res2.val) {
                System.out.println("WRONG");
            }
            res1 = res1.next;
            res2 = res2.next;
        }
        System.out.println("Right");
        return;
    }

    public static DLLNode reverseDoublyLinkedList(DLLNode head) {
        DLLNode pre = null;
        DLLNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.prev = next;
            pre = head;
            head = next;
        }
        return pre;
    }
}
