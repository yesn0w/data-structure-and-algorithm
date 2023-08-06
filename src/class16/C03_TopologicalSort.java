package class16;

import java.util.*;

public class C03_TopologicalSort {

    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbours;

        public DirectedGraphNode(int x) {
            label = x;
            neighbours = new ArrayList<>();
        }
    }

    public static ArrayList<DirectedGraphNode> topologicalSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Integer> inMap = new HashMap<>();
        for (DirectedGraphNode dgNode : graph) {
            inMap.put(dgNode, 0);
        }

        for (DirectedGraphNode dgNode : graph) {
            for (DirectedGraphNode neighbour : dgNode.neighbours) {
                inMap.put(neighbour, inMap.get(neighbour) + 1);
            }
        }

        Queue<DirectedGraphNode> zeroInQueue = new LinkedList<>();
        for (DirectedGraphNode dqNode : inMap.keySet()) {
            if (inMap.get(dqNode) == 0) {
                zeroInQueue.add(dqNode);
            }
        }

        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            DirectedGraphNode cur = zeroInQueue.remove();
            res.add(cur);
            for (DirectedGraphNode neighbour : cur.neighbours) {
                inMap.put(neighbour, inMap.get(neighbour) - 1);
                if (inMap.get(neighbour) == 0) {
                    zeroInQueue.add(neighbour);
                }
            }
        }
        return res;
    }

}
