package class12;

import genericmethods.TreeElements.*;

public class MaxSubBSTSize {

    public static int maxSubBSTSize(NodeWithParent head) {
        if (head == null) {
            return 0;
        }
        return process(head).size;
    }

    public static Info process(NodeWithParent nodeWithParent) {
        if (nodeWithParent == null) {
            return null;
        }
        Info leftInfo = process(nodeWithParent.left);
        Info rightInfo = process(nodeWithParent.right);
        boolean isBST = false;
        int size = 0;
        int min = nodeWithParent.value;
        int max = nodeWithParent.value;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            size = Math.max(size, leftInfo.size);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            size = Math.max(size, rightInfo.size);
        }
        /*
         这样有问题，因为可能返回null
         int min = Math.min(node.value, Math.min(leftInfo.min, rightInfo.min));
         int max = Math.max(node.value, Math.max(leftInfo.max, rightInfo.max));
        */
        if (leftInfo == null && rightInfo == null) {
            size = 1;
            isBST = true;
        }
        if (leftInfo != null && rightInfo == null) {

        }
        if (leftInfo != null && rightInfo != null && leftInfo.isBST && rightInfo.isBST) {
            if (nodeWithParent.value > leftInfo.max && nodeWithParent.value < rightInfo.min) {
                isBST = true;
                size = leftInfo.size + rightInfo.size + 1;
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
