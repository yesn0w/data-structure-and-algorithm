package class12;

import genericmethods.NodeType.*;

public class IsBST {

    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    public static Info process(Node node) {
        if (node == null) {
            return null;
        }
        boolean isBST = true;
        int min = node.value;
        int max = node.value;
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        if (leftInfo != null) {
            isBST &= leftInfo.isBST;
            isBST &= (node.value > leftInfo.max);
        }
        if (rightInfo != null) {
            isBST &= rightInfo.isBST;
            isBST &= (node.value < rightInfo.min);
        }
        return new Info(isBST, min, max);
    }

    public static class Info {
        public boolean isBST;
        public int min;
        public int max;

        public Info(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
}
