package class13;

import genericmethods.NodeType.*;

public class C01_IsCBT {


    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        return false;
    }

    public static Info process(Node node) {
        if (node == null) {
            return new Info(true, true, 0);
        }
        boolean isFull = false;
        boolean isCBT = false;
        int height = 1;
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        height += (Math.max(leftInfo.height, rightInfo.height));
        isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height) {
            isCBT = true;
        } else if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
            isCBT = true;
        } else if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
            isCBT = true;
        } else if (leftInfo.isCBT && leftInfo.isFull && leftInfo.height == rightInfo.height + 1) {
            isCBT = true;
        }
        return new Info(isFull, isCBT, height);
    }

    public static class Info {
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }
}
