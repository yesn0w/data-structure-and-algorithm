package class11;

import java.util.LinkedList;
import java.util.Queue;

public class LevelTraversalBT_BFS {

    public class Node {
        public int value;
        public Node left;
        public Node right;

        public void Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    public static void levelTraversalBT(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.value);
            Node left = cur.left;
            Node right = cur.right;
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
        }
    }

}
