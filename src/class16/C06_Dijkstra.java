package class16;

import genericmethods.GraphElements.*;

import java.util.*;

public class C06_Dijkstra {

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
        nodeHeap.addOrUpdateOrIgnore(from, 0);
        HashMap<Node, Integer> res = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord nodeRecord = nodeHeap.pop();
            Node node = nodeRecord.node;
            int toAdd = nodeRecord.distance;
            for (Edge edge : node.edges) {
                Node to = edge.to;
                nodeHeap.addOrUpdateOrIgnore(to, edge.weight + toAdd);
            }
            res.put(node, toAdd);
        }
        return res;
    }

    public static class NodeHeap {
        private Node[] nodeHeap;
        private HashMap<Node, Integer> indexMap;
        private HashMap<Node, Integer> distanceMap;
        private int size;

        public NodeHeap(int size) {
            nodeHeap = new Node[size];
            indexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 弹出来就代表着已经尘埃落定，不会修改，所以改成-1
        public NodeRecord pop() {
            // 取得
            NodeRecord nodeRecord = new NodeRecord(nodeHeap[0], distanceMap.get(nodeHeap[0]));
            // 调整：和最后一个交换，并且sift改变顺序
            swap(0, size - 1);
            // 这样是不对的，因为距离不该为负，这样会导致距离比较永远更新不了
            // indexMap.remove(nodeHeap[size - 1]);
            // distanceMap.put(nodeHeap[--size], -1);
            indexMap.put(nodeHeap[size - 1], -1);
            distanceMap.remove(nodeHeap[--size]);
            heapify(0);
            return nodeRecord;
        }

        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (!indexMap.containsKey(node)) {
                nodeHeap[size] = node;
                indexMap.put(node, size);
                distanceMap.put(node, distance);
                heapInsert(size);
                size++;
            }
            // 待证：&& distance < distanceMap.get(node)
            if (indexMap.containsKey(node) && indexMap.get(node) != -1) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                heapify(indexMap.get(node));
            }
        }

        public void heapify(int index) {
            int left = index << 2 + 1;
            while (left < size) {
                int right = left + 1;
                int best = right < size && distanceMap.get(nodeHeap[right]) < distanceMap.get(nodeHeap[left]) ? right : left;
                best = distanceMap.get(nodeHeap[left]) < distanceMap.get(nodeHeap[index]) ? left : index;
                if (best == index) {
                    break;
                }
                swap(best, index);
                // 为了下一个循环，不要忘记更新index, left
                index = best;
                left = index << 2 + 1;
            }
        }

        public void heapInsert(int index) {
            while (distanceMap.get(nodeHeap[index]) < distanceMap.get(nodeHeap[(index - 1) >> 2])) {
                swap(index, (index - 1) >> 2);
                index = (index - 1) >> 2;
            }
        }

        // 用不到，因为只会往小变，所以调整时候只需要heapInsert就可以了
        public void sift(int index) {
            heapify(index);
            heapInsert(index);
        }

        public void swap(int index1, int index2) {
            indexMap.put(nodeHeap[index1], index2);
            indexMap.put(nodeHeap[index2], index1);
            Node tmp = nodeHeap[index1];
            nodeHeap[index1] = nodeHeap[index2];
            nodeHeap[index2] = tmp;
        }


    }

    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}
