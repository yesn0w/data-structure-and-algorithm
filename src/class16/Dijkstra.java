package class16;

import com.sun.org.apache.xalan.internal.xsltc.dom.NodeSortRecord;
import genericmethods.GraphElements.*;

import java.util.*;

public class Dijkstra {

    public static Map<Node, Integer> dijkstra(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
//        for (Edge edge : from.edges) {
//            distanceMap.put(edge.to, edge.weight);
//        }
        // 再从里面拿出最小距离的点，重复以上过程
        HashSet<Node> selectedNodes = new HashSet<>();
        // 找到integer最小的边，找到to节点
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        // （当前这条边长度 + to所有边连接的所有节点，距离）与distanceMap里面的距离进行比较，小的更新
        while (minNode != null) {
            int curDistance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, curDistance + edge.weight);
                }
                else {
                    distanceMap.put(toNode, Math.min(curDistance + edge.weight, distanceMap.get(toNode)));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    private static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!selectedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }



    public static Map<Node, Integer> dijkstraWithHeap(Node from, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        HashMap<Node, Integer> res = new HashMap<>();
        res.put(from, 0);
        while (!nodeHeap.isEmpty()) {

        }
    }

    public static class NodeHeap {
        public Node[] nodeHeap;
        public HashMap<Node, Integer> indexMap;
        public int size;

        public NodeHeap(int size) {
            nodeHeap = new Node[size];
            indexMap = new HashMap<>();
            this.size = size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void swap(Node node1, Node node2) {

        }


    }
}
