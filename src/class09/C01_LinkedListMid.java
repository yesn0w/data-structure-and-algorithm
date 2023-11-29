package class09;

public class C01_LinkedListMid {

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
     */

    public class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            next = null;
        }
    }

    // 1->2->3->4->5
    // 1->2->3
    // 奇数，中点
    // 偶数，上中点
    // 口诀：head = 0, head.next = 1, head.next.next = 2
    // slow = 1, fast = 2
    public static Node midOrFirstMidNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 奇数，中点
    // 偶数，下中点
    // 口诀：slow = 1, fast = 1
    public static Node midOrSecondMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.next.next == null) {
            return head.next;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 奇数：中点前一个
    // 偶数：上中点前一个
    // 口诀：slow = 0, fast = 2
    public static Node preMidOrPreFirstMidNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 奇数：中点前一个点
    // 偶数：上中点
    // 口诀：slow = 0, fast = 1
    public static Node preMidOrFirstMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.next.next == null) {
            return head.next;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
