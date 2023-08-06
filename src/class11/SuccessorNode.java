package class11;

import genericmethods.TreeElements.*;

public class SuccessorNode {

    // 求中序遍历下，当前节点的下一个节点
    public static NodeWithParent successorNode(NodeWithParent nodeWithParent) {
        if (nodeWithParent.right != null) {
            NodeWithParent cur = nodeWithParent.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }
        NodeWithParent cur = nodeWithParent;
        while (cur.parent != null) {
            NodeWithParent parent = cur.parent;
            if (cur == parent.left) {
                return parent;
            }
            else {
                cur = parent;
            }
        }
        return null;
    }

}
