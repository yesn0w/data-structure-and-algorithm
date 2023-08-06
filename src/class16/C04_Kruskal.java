package class16;

import genericmethods.GraphElements.*;

import java.util.*;

public class C04_Kruskal {

    public static class UnionFind {
        private HashMap<Node, Node> father;
        private HashMap<Node, Integer> size;

        public UnionFind() {
            father = new HashMap<>();
            size = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes) {
            father.clear();
            size.clear();
            for (Node node : nodes) {
                father.put(node, node);
                size.put(node, 1);
            }
        }

        public Node findFather(Node node) {
            Stack<Node> stack = new Stack<>();
            Node cur = node;
            while (cur != father.get(cur)) {
                stack.push(cur);
                cur = father.get(cur);
            }
            while (!stack.isEmpty()) {
                Node tmp = stack.pop();
                father.put(tmp, cur);
            }
            return cur;
        }

        public void union(Node node1, Node node2) {
            // 这个边界条件，不要忘记
            if (node1 == null || node2 == null) {
                return;
            }
            Node node1Father = findFather(node1);
            Node node2Father = findFather(node2);
            if (node1Father != node2Father) {
                int size1 = size.get(node1);
                int size2 = size.get(node2);
                if (size1 < size2) {
                    father.put(node1, node2Father);
                    size.put(node2Father, size.get(node2Father) + size.get(node1Father));
                    size.remove(node1Father);
                }
                else {
                    father.put(node2, node1Father);
                    size.put(node1Father, size.get(node1Father) + size.get(node2Father));
                    size.remove(node2Father);
                }
            }
        }

        public boolean isSameSet(Node node1, Node node2) {
            return findFather(node1) == findFather(node2);
        }
    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        PriorityQueue<Edge> pq = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) {
            pq.add(edge);
        }
        Set<Edge> res = new HashSet<>();
        while (!pq.isEmpty()) {
            Edge cur = pq.remove();
            if (!unionFind.isSameSet(cur.from, cur.to)) {
                res.add(cur);
                unionFind.union(cur.from, cur.to);
            }
        }
        return res;
    }
}
