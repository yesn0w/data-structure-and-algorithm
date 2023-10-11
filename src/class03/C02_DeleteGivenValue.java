package class03;

import genericmethods.ListElements.*;

public class C02_DeleteGivenValue {

    /**
     * 在链表中删除指定值的所有节点
     */

    public static Node removeValue(Node head, int target) {
        // 1. head来到第一个不需要删的位置（先解决边界条件）
        while (head != null) {
            if (head.val != target) {
                break;
            }
            head = head.next;
        }
        // 2. 删除head后面满足要求的节点
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.val == target) {
                pre.next = cur.next;
            }
            else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
