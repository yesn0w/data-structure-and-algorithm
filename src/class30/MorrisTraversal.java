package class30;

import genericmethods.TreeElements.*;

public class MorrisTraversal {

    /**
     * 为了将树的遍历在时间复杂度为O(N)的前提下，空间复杂度提高到O(1)
     * 一般套路：先用递归套路，再用morris优化
     * @param head 根节点
     */
    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node tmp = null;
        while (cur != null) {
            tmp = cur.left;
            if (tmp != null) {
                while (tmp.right != cur && tmp.right != null) {
                    tmp = tmp.right;
                }
                if (tmp.right == null) {
                    tmp.right = cur;
                    cur = cur.left;
                    // 为了不让走到 tmp = tmp.right
                    continue;
                }
                else {
                    tmp.right = null;
                }
            }
            tmp = tmp.right;
        }
    }

    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node tmp = null;
        while (cur != null) {
            tmp = cur.left;
            if (tmp != null) {
                while (tmp.right != cur && tmp.right != null) {
                    tmp = tmp.right;
                }
                if (tmp.right == null) {
                    System.out.println(cur.value + " ");
                    tmp.right = cur;
                    cur = cur.left;
                    // 为了不让走到 tmp = tmp.right
                    continue;
                }
                else {
                    tmp.right = null;
                }
            }
            else {
                // 要保证不进入上一个else中，所以在这里else，而不是直接写到tmp = tmp.right上面
                System.out.println(cur.value + " ");
            }
            tmp = tmp.right;
        }
    }

    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node tmp = null;
        while (cur != null) {
            tmp = cur.left;
            if (tmp != null) {
                while (tmp.right != cur && tmp.right != null) {
                    tmp = tmp.right;
                }
                if (tmp.right == null) {
                    System.out.println(cur.value + " ");
                    tmp.right = cur;
                    cur = cur.left;
                    // 为了不让走到 tmp = tmp.right
                    continue;
                }
                else {
                    tmp.right = null;
                }
            }
            System.out.println(cur.value + " ");
            tmp = tmp.right;
        }
    }

    // todo: 反转链表
    public static void morrisPost(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node tmp = null;
        while (cur != null) {
            tmp = cur.left;
            if (tmp != null) {
                while (tmp.right != cur && tmp.right != null) {
                    tmp = tmp.right;
                }
                if (tmp.right == null) {
                    System.out.println(cur.value + " ");
                    tmp.right = cur;
                    cur = cur.left;
                    // 为了不让走到 tmp = tmp.right
                    continue;
                }
                else {
                    tmp.right = null;
                }
            }
            System.out.println(cur.value + " ");
            tmp = tmp.right;
        }
    }
}
