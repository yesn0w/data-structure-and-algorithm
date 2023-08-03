package class11;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeTree {

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

    public static void serializeTree(Node head) {
        LinkedList<String> list = new LinkedList<>();
        processSerialize(head, list);
    }

    public static void processSerialize(Node node, LinkedList<String> list) {
        if (node == null) {
            list.add("#");
            return;
        }
        list.add(String.valueOf(node.value));
        processSerialize(node.left, list);
        processSerialize(node.right, list);
    }

    public static Node deserializeTree(Queue<String> queue) {
        return processDeserialize(queue);
    }

    public static Node processDeserialize(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String cur = queue.remove();
        if (cur.equals("#")) {
            return null;
        }
        Node node = new Node(Integer.parseInt(cur));
        node.left = processDeserialize(queue);
        node.right = processDeserialize(queue);
        return node;
    }
}
