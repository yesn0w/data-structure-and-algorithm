package class37;

public class C02_SlidingWindowMedian {

    /**
     * 有一个滑动窗口：
     * 1）L是滑动窗口最左位置、R是滑动窗口最右位置，一开始LR都在数组左侧
     * 2）任何一步都可能R往右动，表示某个数进了窗口
     * 3）任何一步都可能L往右动，表示某个数出了窗口
     * 想知道每一个窗口状态的中位数
     */

    public static class SBTNode<K extends Comparable<K>> {
        public K key;
        public int size;
        public SBTNode<K> left;
        public SBTNode<K> right;

        public SBTNode(K k) {
            key = k;
            size = 1;
        }
    }

    public static class SizeBalancedTreeMap<K extends Comparable<K>> {
        private SBTNode<K> root;

        private SBTNode<K> leftRotate(SBTNode<K> cur) {
            SBTNode<K> right = cur.right;
            cur.right = right.left;
            right.left = cur;
            right.size = cur.size;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            return right;
        }

        private SBTNode<K> rightRotate(SBTNode<K> cur) {
            SBTNode<K> left = cur.left;
            cur.left = left.right;
            left.right = cur;
            left.size = cur.size;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            return left;
        }

        private SBTNode<K> maintain(SBTNode<K> cur) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.left == null ? 0 : cur.left.size;
            int rightSize = cur.right == null ? 0 : cur.right.size;
            int leftLeftSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
            int leftRightSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
            int rightLeftSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
            int rightRightSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;
            if (leftLeftSize > rightSize) {
                cur = rightRotate(cur);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            else if (leftRightSize > rightSize) {
                cur.left = leftRotate(cur.left);
                cur = rightRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            else if (rightRightSize > leftSize) {
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            }
            else if (rightLeftSize > leftSize) {
                cur.right = rightRotate(cur.right);
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            return cur;
        }

        private SBTNode<K> add(SBTNode<K> cur, K key) {
            if (cur == null) {
                return new SBTNode<K>(key);
            }
            else {
                cur.size++;
                if (key.compareTo(cur.key) < 0) {
                    cur.left = add(cur.left, key);
                }
                else {
                    cur.right = add(cur.right, key);
                }
                return maintain(cur);
            }
        }

        private SBTNode<K> delete(SBTNode<K> cur, K key) {
            cur.size--;
            if (key.compareTo(cur.key) < 0) {
                cur.left = delete(cur.left, key);
            }
            else if (key.compareTo(cur.key) > 0) {
                cur.right = delete(cur.right, key);
            }
            else {
                if (cur.left == null && cur.right == null) {
                    cur = null;
                }
                else if (cur.left == null && cur.right != null) {
                    cur = cur.right;
                }
                else if (cur.left != null && cur.right == null) {
                    cur = cur.left;
                }
                else {
                    SBTNode<K> pre = null;
                    SBTNode<K> tmp = cur.left;
                    tmp.size--;
                    while (tmp.right != null) {
                        pre = tmp;
                        tmp = tmp.right;
                        tmp.size--;
                    }
                    if (pre != null) {
                        pre.right = tmp.left;
                        tmp.left = cur.left;
                    }
                    tmp.right = cur.right;
                    tmp.size = tmp.right.size + (tmp.left == null ? 0 : tmp.left.size) + 1;
                    cur = tmp;
                }
            }
            return cur;
        }

        private SBTNode<K> getIndex(SBTNode<K> cur, int kth) {
            if (kth == (cur.left == null ? 0 : cur.left.size) + 1) {
                return cur;
            }
            else if (kth <= (cur.left == null ? 0 : cur.left.size)) {
                return getIndex(cur.left, kth);
            }
            else {
                return getIndex(cur.right, kth - (cur.left == null ? 0 : cur.left.size) - 1);
            }
        }

        public K getIndexKey(int index) {
            if (index < 0 || index >= root.size) {
                throw new RuntimeException("invalid parameter.");
            }
            return getIndex(root, index + 1).key;
        }

        public void add(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K> lastNode = findLastIndex(key);
            if (lastNode == null || key.compareTo(lastNode.key) != 0) {
                root = add(root, key);
            }
        }

        public void remove(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            if (containsKey(key)) {
                root = delete(root, key);
            }
        }

        public boolean containsKey(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K> lastNode = findLastIndex(key);
            return lastNode != null && key.compareTo(lastNode.key) == 0 ? true : false;
        }

        private SBTNode<K> findLastIndex(K key) {
            SBTNode<K> pre = root;
            SBTNode<K> cur = root;
            while (cur != null) {
                pre = cur;
                if (key.compareTo(cur.key) == 0) {
                    break;
                }
                else if (key.compareTo(cur.key) < 0) {
                    cur = cur.left;
                }
                else {
                    cur = cur.right;
                }
            }
            return pre;
        }

        public int size() {
            return root == null ? 0 : root.size;
        }
    }



    public static class Node implements Comparable<Node> {
        public int index;
        public int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return value != o.value ? Integer.valueOf(value).compareTo(o.value) : Integer.valueOf(index).compareTo(o.index);
        }
    }


    // node: index, nums[index]
    // 使用node对k包装一层，放在树里的index, 以及nums[index]对应的值，值可以重复，，用index找到值

    public static double[] medianSlidingWindow(int[] nums, int k) {
        SizeBalancedTreeMap<Node> map = new SizeBalancedTreeMap<>();
        for (int i = 0; i < k - 1; i++) {
            map.add(new Node(i, nums[i]));
        }
        double[] res = new double[nums.length - k + 1];
        int index = 0;
        for (int i = k - 1; i < nums.length - k + 1; i++) {
            map.add(new Node(i, nums[i]));
            if (map.size() % 2 == 0) {
                Node downMid = map.getIndexKey(map.size() / 2 - 1);
                Node upMid = map.getIndexKey(map.size() / 2);
                res[index++] = (double) downMid.value;
            }
            else {
                Node mid = map.getIndexKey(map.size() / 2);
                res[index++] = (double) mid.value;
            }
            map.remove(new Node(i - k + 1, nums[i - k + 1]));
        }
        return res;
    }

}
