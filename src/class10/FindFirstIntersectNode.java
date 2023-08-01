package class10;

public class FindFirstIntersectNode {

    public class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
            next = null;
        }
    }

    public static Node findFirstIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = loopEntranceNode(head1);
        Node loop2 = loopEntranceNode(head2);
        if (loop1 == null && loop2 == null) {
            return findListIntersectNode(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return findLoopIntersectNode(head1, head2);
        }
        return null;
    }

    private static Node findListIntersectNode(Node head1, Node head2) {
        int diff = 0;
        Node cur1 = head1;
        while (cur1 != null) {
            diff++;
            cur1 = cur1.next;
        }
        Node cur2 = head2;
        while (cur2 != null) {
            diff--;
            cur2 = cur2.next;
        }
        if (diff > 0) {
            cur1 = head1;
            while (diff > 0) {
                cur1 = cur1.next;
                diff--;
            }
        }
        else {

        }

        return null;
    }

    private static Node findLoopIntersectNode(Node head1, Node head2) {
    }


    public static boolean isLoop(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    public static Node loopEntranceNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

}
