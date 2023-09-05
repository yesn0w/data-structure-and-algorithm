package class27;

import genericmethods.TreeElements.*;

import java.util.ArrayList;

public class C03_TreeEqual {

    public static boolean isSubtree(Node big, Node small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }
        ArrayList<String> preList = preTraversal(big);
        ArrayList<String> smallList = preTraversal(small);
        String[] bigStr = new String[preList.size()];
        for (int i = 0; i < bigStr.length; i++) {
            bigStr[i] = preList.get(i);
        }
        String[] smallStr = new String[smallList.size()];
        for (int i = 0; i < smallStr.length; i++) {
            smallStr[i] = smallList.get(i);
        }
        return getIndexOf(bigStr, smallStr) != -1;
    }

    public static int getIndexOf(String[] s1, String[] s2) {
        if (s2 == null || s1 == null || s1.length == 0 || s2.length < s1.length) {
            return -1;
        }
        int[] next = getNext(s2);
        int i1 = 0;
        int i2 = 0;
        while (i1 < s1.length && i2 < s2.length) {
            if (s1[i1].equals(s2[i2])) {
                i1++;
                i2++;
            }
            else if (next[i2] == -1) {
                i1++;
            }
            else {
                i2 = next[i2];
            }
        }
        return i2 == s2.length ? i1 - i2 : -1;
    }

    public static int[] getNext(String[] s) {
        if (s.length == 1) {
            return new int[]{-1};
        }
        if (s.length == 2) {
            return new int[]{-1, 0};
        }
        int[] next = new int[s.length];
        int index = 0;
        int i = 0;
        while (index < s.length) {
            if (s[index - 1].equals(s[i])) {
                next[index] = ++i;
            }
            else if (i > 0) {
                i = next[i];
            }
            else {
                next[index++] = 0;
            }
        }
        return next;
    }

    public static ArrayList<String> preTraversal(Node node) {
        ArrayList<String> res = new ArrayList<>();
        pre(node, res);
        return res;
    }

    public static void pre(Node node, ArrayList<String> res) {
        if (node == null) {
            res.add(null);
        }
        res.add(String.valueOf(node.value));
        pre(node.left, res);
        pre(node.right, res);
    }

}
