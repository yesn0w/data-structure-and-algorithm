package class10;

import java.util.Stack;

public class NonrecursiveTraversalBT {

    public class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    public static void preorderTraversal(Node root) {
        Stack<Node> stack = new Stack<>();
        if (root == null) {
            return;
        }
        stack.push(root);
        while (stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.value + " ");
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    public static void postorderTraversal(Node root) {
        Stack<Node> stack = new Stack<>();
        Stack<Node> reverse = new Stack<>();
        if (root == null) {
            return;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            reverse.push(cur);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        while (!reverse.isEmpty()) {
            System.out.println(reverse.pop().value + " ");
        }
    }

    public static void inorderTraversal(Node root) {
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        // cur != null 保证访问右孩子
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            else {
                cur = stack.pop();
                System.out.println(cur.value + " ");
                cur = cur.right;
            }
        }
        System.out.println();
    }
}
