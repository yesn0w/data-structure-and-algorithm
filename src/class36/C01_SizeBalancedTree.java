package class36;

public class C01_SizeBalancedTree {

    public static class SBTNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public SBTNode<K, V> left;
        public SBTNode<K, V> right;
        public int size;

        public SBTNode(K key, V value) {
            this.key = key;
            this.value = value;
            size = 1;
        }
    }

    public static class SizeBalancedTreeMap<K extends Comparable<K>, V> {
        private SBTNode<K, V> root;


        // 内部方法，核心方法
        // 一级核心方法，关键的关键
        private SBTNode<K, V> rightRotate(SBTNode<K, V> cur) {
            SBTNode<K, V> left = cur.left;
            cur.left = left.right;
            left.right = cur;
            // size属性也要调整
            left.size = cur.size;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            return left;
        }

        private SBTNode<K, V> leftRotate(SBTNode<K, V> cur) {
            SBTNode<K, V> right = cur.right;
            cur.right = right.left;
            right.left = cur;
            right.size = cur.size;
            cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
            return right;
        }

        private SBTNode<K, V> maintain(SBTNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.left == null ? 0 : cur.left.size;
            int rightSize = cur.right == null ? 0 : cur.right.size;
            int leftLeftSize = leftSize == 0 || cur.left.left == null ? 0 : cur.left.left.size;
            int leftRightSize = leftSize == 0 || cur.left.right == null ? 0 : cur.left.right.size;
            int rightLeftSize = rightSize == 0 || cur.right.left == null ? 0 : cur.right.left.size;
            int rightRightSize = rightSize == 0 || cur.right.right == null ? 0 : cur.right.right.size;
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
            else if (rightLeftSize > leftSize) {
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            }
            else if (rightRightSize > leftSize) {
                cur.right = rightRotate(cur.right);
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            return cur;
        }

        private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new SBTNode<K, V>(key, value);
            }
            cur.size++;
            if (key.compareTo(cur.key) < 0) {
                cur.left = add(cur.left, key, value);
            }
            else {
                cur.right = add(cur.right, key, value);
            }
            return maintain(cur);
        }

        // key一定存在，所以一定删除掉
        private SBTNode<K, V> delete(SBTNode<K, V> cur, K key) {
            cur.size--;
            if (key.compareTo(cur.key) < 0) {
                delete(cur.left, key);
            }
            else if (key.compareTo(cur.key) > 0) {
                delete(cur.right, key);
            }
            else {
                if (cur.left == null && cur.right == null) {
                    cur = null;
                }
                else if (cur.left == null) {
                    cur = cur.right;
                }
                else if (cur.right == null) {
                    cur = cur.left;
                }
                else {
                    SBTNode<K, V> pre = null;
                    SBTNode<K, V> tmp = cur.right;
                    // 时刻注意修整size
                    tmp.size--;
                    while (tmp.left != null) {
                        pre = tmp;
                        tmp = tmp.left;
                        // 时刻注意修正size
                        tmp.size--;
                    }
                    if (pre != null) {
                        pre.left = tmp.right;
                        tmp.right = cur.right;
                    }
                    tmp.left = cur.left;
                    tmp.size = tmp.left.size + (tmp.right == null ? 0 : tmp.right.size) + 1;
                    cur = tmp;
                }
            }
            return cur;
        }

        // 二级核心方法
        // 第多少多少个
        // todo: null处可做优化
        private SBTNode<K, V> getIndex(SBTNode<K, V> cur, int kth) {
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

        // 找到小于key的最后一个节点
        private SBTNode<K, V> findLastIndex(K key) {
            SBTNode<K, V> pre = root;
            SBTNode<K, V> cur = root;
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

        // 找到不小于的最后一个节点
        private SBTNode<K, V> findFirstNoSmallIndex(K key) {
            SBTNode<K, V> res = null;
            SBTNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    res = cur;
                    break;
                }
                else if (key.compareTo(cur.key) < 0) {
                    res = cur;
                    cur = cur.left;
                }
                else {
                    cur = cur.right;
                }
            }
            return res;
        }

        private SBTNode<K, V> findLastNoBigIndex(K key) {
            SBTNode<K, V> res = null;
            SBTNode<K, V> cur = root;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    res = cur;
                    break;
                }
                else if (key.compareTo(cur.key) < 0) {
                    cur = cur.left;
                }
                else {
                    res = cur;
                    cur = cur.right;
                }
            }
            return res;
        }



        // 普通方法，外部调用的方法
        public int size() {
            return root == null ? 0 : root.size;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter");
            }
            SBTNode<K, V> lastNode = findLastIndex(key);
            return lastNode != null && key.compareTo(lastNode.key) == 0;
        }

        public void put(K key, V value) {
            if (key == null) {
                throw new RuntimeException("invalid parameter");
            }
            SBTNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.key) == 0) {
                lastNode.value = value;
            }
            else {
                root = add(root, key, value);
            }
        }

        public V get(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter");
            }
            SBTNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.key) == 0) {
                return lastNode.value;
            }
            return null;
        }

        public void remove(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter");
            }
            if (containsKey(key)) {
                root = delete(root, key);
            }
        }

        public K getIndexKey(int index) {
            if (index < 0 || index >= this.size()) {
                throw new RuntimeException("invalid parameter");
            }
            return getIndex(root, index + 1).key;
        }

        public V getIndexValue(int index) {
            if (index < 0 || index >= this.size()) {
                throw new RuntimeException("invalid parameter");
            }
            return getIndex(root, index + 1).value;
        }

        public K firstKey() {
            if (root == null) {
                return null;
            }
            SBTNode<K, V> cur = root;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur.key;
        }

        public K lastKey() {
            if (root == null) {
                return null;
            }
            SBTNode<K, V> cur = root;
            while (cur.right != null) {
                cur = cur.right;
            }
            return cur.key;
        }

        public K floorKey(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter");
            }
            SBTNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
            return lastNoBigNode == null ? null : lastNoBigNode.key;
        }

        public K ceilingKey(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter");
            }
            SBTNode<K, V> firstNoSmallNode = findFirstNoSmallIndex(key);
            return firstNoSmallNode == null ? null : firstNoSmallNode.key;
        }
    }
}
