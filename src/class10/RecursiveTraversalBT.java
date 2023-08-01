package class10;

public class RecursiveTraversalBT {

    public class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }


    public static void baseTraversal(Node node) {
        if (node == null) {
            return;
        }
        // System.out.println(node.value);
        baseTraversal(node.left);
        //
        baseTraversal(node.right);
        //
    }

}
