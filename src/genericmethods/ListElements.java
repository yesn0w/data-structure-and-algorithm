package genericmethods;

public class ListElements {

    public static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static class DLLNode {
        public int val;
        public DLLNode prev;
        public DLLNode next;

        public DLLNode(int val) {
            this.val = val;
        }
    }

}
