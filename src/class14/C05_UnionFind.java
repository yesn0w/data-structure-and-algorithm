package class14;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class C05_UnionFind {

    public static class UnionFind<V> {
        public HashMap<V, V> father;
        public HashMap<V, Integer> size;

        public UnionFind(List<V> values) {
            father = new HashMap<>();
            size = new HashMap<>();
            for (V cur : values) {
                father.put(cur, cur);
                size.put(cur, 1);
            }
        }

        public V findFather(V cur) {
            Stack<V> stack = new Stack<>();
            while (cur != father.get(cur)) {
                stack.push(cur);
                cur = father.get(cur);
            }
            while (!stack.isEmpty()) {
                father.put(stack.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b) {
            return findFather(a) == findFather(b);
        }

        public void union(V a, V b) {
            V aFather = findFather(a);
            V bFather = findFather(b);
            if (aFather == null || bFather == null) {
                return;
            }
            if (aFather != bFather) {
                int aFatherSize = size.get(aFather);
                int bFatherSize = size.get(bFather);
                if (aFatherSize < bFatherSize) {
                    father.put(aFather, bFather);
                    size.remove(aFather);
                    size.put(bFather, aFatherSize + bFatherSize);
                }
                else {
                    father.put(bFather, aFather);
                    size.remove(bFather);
                    size.put(aFather, aFatherSize + bFatherSize);
                }
            }
        }

        public int sets() {
            return size.size();
        }
    }

}
