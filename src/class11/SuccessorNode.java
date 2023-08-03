package class11;

public class SuccessorNode {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int value) {
            this.value = value;
            left = null;
            right = null;
            parent = null;
        }
    }

    // 求中序遍历下，当前节点的下一个节点
    public static Node successorNode(Node node) {
        if (node.right != null) {
            Node cur = node.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }
        Node cur = node;
        while (cur.parent != null) {
            Node parent = cur.parent;
            if (cur == parent.left) {
                return parent;
            }
            else {
                cur = parent;
            }
        }
        return null;
    }

}
