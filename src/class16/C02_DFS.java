package class16;

import genericmethods.GraphElements.*;

import java.util.HashSet;
import java.util.Stack;

public class C02_DFS {

    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        set.add(node);
        stack.push(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    System.out.println(next.value);
                    stack.push(next);
                    set.add(next);
                    // break必须加，不然会访问其他孩子
                    break;
                }
            }
        }
    }

}
