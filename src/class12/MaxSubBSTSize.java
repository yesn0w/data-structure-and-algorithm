package class12;

import genericmethods.NodeType.*;

public class MaxSubBSTSize {

    public static int maxSubBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).size;
    }

    public static Info process(Node node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        boolean isBST = false;
        int size = 0;
        int min = node.value;
        int max = node.value;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        // 这样有问题，因为可能返回null
        // int min = Math.min(node.value, Math.min(leftInfo.min, rightInfo.min));
        // int max = Math.max(node.value, Math.max(leftInfo.max, rightInfo.max));
        if (leftInfo != null && rightInfo != null) {
            if (leftInfo.isBST && !rightInfo.isBST) {
                size = leftInfo.size;
            } else if (leftInfo != null && rightInfo != null && !leftInfo.isBST && rightInfo.isBST) {
                size = rightInfo.size;
            } else if (leftInfo != null && rightInfo != null && leftInfo.isBST && rightInfo.isBST) {
                if (node.value > leftInfo.max && node.value < rightInfo.min) {
                    isBST = true;
                    size = leftInfo.size + rightInfo.size + 1;
                }
                else {
                    isBST = true;
                    size = Math.max(leftInfo.size, rightInfo.size);
                }
            }
        }

        return new Info(isBST, size, min, max);
    }

    public static class Info {
        public boolean isBST;
        public int size;
        public int min;
        public int max;

        public Info(boolean isBST, int size, int min, int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }
}
