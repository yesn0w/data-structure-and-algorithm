package class11;

import java.util.LinkedList;
import java.util.Queue;
import genericmethods.TreeElements.*;

public class TreeMaxWidth {

    public static int treeMaxWidth(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head;
        Node nextEnd = head;
        int curWidth = 0;
        int maxWidth = 0;
        curEnd = head;
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            curWidth++;
            Node left = cur.left;
            Node right = cur.right;
            if (left != null) {
                queue.add(left);
                nextEnd = left;
            }
            if (right != null) {
                queue.add(right);
                nextEnd = right;
            }
            if (cur == curEnd) {
                maxWidth = Math.max(maxWidth, curWidth);
                curWidth = 0;
                curEnd = nextEnd;
                nextEnd = null;
            }
        }
        return maxWidth;
    }

}
