package class12;

import genericmethods.TreeElements.*;

public class IsBST {

    public static boolean isBST(NodeWithParent head) {
        if (head == null) {
            return true;
        }
        return process(head).isBST;
    }

    public static Info process(NodeWithParent nodeWithParent) {
        if (nodeWithParent == null) {
            return null;
        }
        boolean isBST = true;
        int min = nodeWithParent.value;
        int max = nodeWithParent.value;
        Info leftInfo = process(nodeWithParent.left);
        Info rightInfo = process(nodeWithParent.right);
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
            isBST &= (nodeWithParent.value > leftInfo.max);
        }
        if (rightInfo != null) {
            isBST &= rightInfo.isBST;
            isBST &= (nodeWithParent.value < rightInfo.min);
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
