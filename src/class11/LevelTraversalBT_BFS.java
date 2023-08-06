package class11;

import java.util.LinkedList;
import java.util.Queue;
import genericmethods.TreeElements.*;

public class LevelTraversalBT_BFS {

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
