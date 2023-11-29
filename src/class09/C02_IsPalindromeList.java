package class09;

import genericmethods.ListElements.*;

public class C02_IsPalindromeList {

    /**
     * 给定一个单链表的头节点head，请判断该链表是否为回文结构
     */

    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        if (head.next.next == null && (head.val == head.next.val)) {
            return true;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalf = reverseList(slow.next);
        Node tmp = secondHalf;
        printList(secondHalf);
        Node firstHalf = head;
        while (firstHalf != null && secondHalf != null) {
            if (firstHalf.val != secondHalf.val) {
                slow.next = reverseList(tmp);
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        slow.next = reverseList(tmp);
        printList(slow.next);
        return true;
    }

    public static Node reverseList(Node node) {
        Node cur = node;
        Node pre = null;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void printList(Node node) {
        while (node != null) {
            System.out.print(node.val + ", ");
            node = node.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
//        Node node1 = new Node(1);
//        Node node2 = new Node(2);
//        Node node3 = new Node(1);
//        node1.next = node2;
//        node2.next = node3;
//        printList(node1);
//        System.out.println(isPalindrome(node1));
//        printList(node1);

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(3);
        Node node5 = new Node(2);
        Node node6 = new Node(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        printList(node1);
        System.out.println(isPalindrome(node1));
        printList(node1);

        System.out.println("-------------------");

        Node node11 = new Node(1);
        Node node12 = new Node(2);
        Node node13 = new Node(3);
        Node node14 = new Node(2);
        Node node15 = new Node(1);
        node11.next = node12;
        node12.next = node13;
        node13.next = node14;
        node14.next = node15;
        printList(node11);
        System.out.println(isPalindrome(node11));
        printList(node11);

        System.out.println("-------------------");

        Node node21 = new Node(1);
        Node node22 = new Node(2);
        Node node23 = new Node(3);
        Node node24 = new Node(4);
        node21.next = node22;
        node22.next = node23;
        node23.next = node24;
        printList(node21);
        System.out.println(isPalindrome(node21));
        printList(node21);
    }

}
