package class12;

import genericmethods.TreeElements.*;

public class IsBalanced {

    public static class Info {
        public boolean isBalanced = true;
        public int height = 0;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static boolean isBalanced(NodeWithParent head) {
        if (head == null) {
            return true;
        }
        return process(head).isBalanced;
    }

    public static Info process(NodeWithParent nodeWithParent) {
        if (nodeWithParent == null) {
            return new Info(true, 0);
        }
        boolean isBalanced = true;
        int height = 1;
        Info leftInfo = process(nodeWithParent.left);
        Info rightInfo = process(nodeWithParent.right);
        isBalanced = leftInfo.isBalanced & rightInfo.isBalanced & (Math.abs(leftInfo.height - rightInfo.height) < 2);
        height += Math.max(leftInfo.height, rightInfo.height);
        return new Info(isBalanced, height);
    }
}
