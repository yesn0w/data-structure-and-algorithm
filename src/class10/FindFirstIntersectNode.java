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

    public static boolean isCircular(Node head) {
        if (head == null || head.next == null) {
            return false;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast != null || fast.next != null || fast.next.next != null) {

        }
    }

}
