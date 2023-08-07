package class16;

import genericmethods.GraphElements.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class C05_Prim {

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(new EdgeComparator());
        HashSet<Node> nodeSet = new HashSet<>();
        Set<Edge> res = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            if (!nodeSet.contains(node)) {
                for (Edge edge : node.edges) {
                    pq.add(edge);
                }
                while (!pq.isEmpty()) {
                    Edge cur = pq.remove();
                    Node to = cur.to;
                    if (!nodeSet.contains(to)) {
                        nodeSet.add(to);
                        res.add(cur);
                        for (Edge toEdge : to.edges) {
                            pq.add(toEdge);
                        }
                    }
                }
            }
            // 用break去防止森林的出现
            // break
        }
        return res;
    }

}
