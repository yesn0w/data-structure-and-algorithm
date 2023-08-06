package genericmethods;

public class TreeElements {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    public static class NodeWithParent {
        public int value;
        public NodeWithParent left;
        public NodeWithParent right;
        public NodeWithParent parent;

        public NodeWithParent(int value) {
            this.value = value;
            left = null;
            right = null;
            parent = null;
        }
    }
}
