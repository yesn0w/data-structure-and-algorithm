package class35;

public class C01_AVLTreeMap {

    public static class AVLNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        AVLNode<K, V> left;
        AVLNode<K, V> right;
        public int height;

        public AVLNode(K key, V value) {
            this.key = key;
            this.value = value;
            height = 1;
        }
    }

    public static class AVLTreeMap<K extends Comparable<K>, V> {
        private AVLNode<K, V> root;
        private int size;

        public AVLTreeMap() {
            root = null;
            size = 0;
        }

        private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> left = cur.left;
            cur.left = left.right;
            left.right = cur;
            cur.height = Math.max(cur.left == null ? 0 : cur.left.height, cur.right == null ? 0 : cur.right.height) + 1;
            left.height = Math.max(left.left == null ? 0 : left.left.height, left.right == null ? 0 : left.right.height) + 1;
            return left;
        }

        private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> right = cur.right;
            cur.right = right.left;
            right.left = cur;
            cur.height = Math.max(cur.right == null ? 0 : cur.right.height, cur.left == null ? 0 : cur.left.height) + 1;
            right.height = Math.max(right.right == null ? 0 : right.right.height, right.left == null ? 0 : right.left.height) + 1;
            return right;
        }

        private AVLNode<K, V> maintain(AVLNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            int leftHeight = cur.left == null ? 0 : cur.left.height;
            int rightHeight = cur.right == null ? 0 : cur.right.height;
            int leftLeftHeight = cur.left == null ? 0 : (cur.left.left == null ? 0 : cur.left.left.height);
            int leftRightHeight = cur.left == null ? 0 : (cur.left.right == null ? 0 : cur.left.right.height);
            int rightLeftHeight = cur.right == null ? 0 : (cur.right.left == null ? 0 : cur.right.left.height);
            int rightRightHeight = cur.right == null ? 0 : (cur.right.right == null ? 0 : cur.right.right.height);
            if (leftHeight - rightHeight > 1) {
                if (leftLeftHeight >= leftRightHeight) {
                    cur = rightRotate(cur);
                }
                else {
                    cur.left = leftRotate(cur.left);
                    cur = rightRotate(cur);
                }
            }
            else if (rightHeight - leftHeight > 1) {
                if (rightRightHeight >= rightLeftHeight) {
                    cur = leftRotate(cur);
                }
                else {
                    cur.right = rightRotate(cur.right);
                    cur = leftRotate(cur);
                }
            }
            return cur;
        }

        private AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new AVLNode<K, V>(key, value);
            }
            if (key.compareTo(cur.key) < 0) {
                cur.left = add(cur.left, key, value);
            }
            else if (key.compareTo(cur.key) > 0) {
                cur.right = add(cur.right, key, value);
            }
            // 不要忘记调整高度
            cur.height = Math.max(cur.left == null ? 0 : cur.left.height, cur.right == null ? 0 : cur.right.height) + 1;
            return maintain(cur);
        }

        private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {
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
                    AVLNode<K, V> tmp = cur.left;
                    while (tmp.right != null) {
                        tmp = tmp.right;
                    }
                    cur.left = delete(cur.left, tmp.key);
                    tmp.left = cur.left;
                    tmp.right = cur.right;
                    cur = tmp;
                }
            }
            if (cur != null) {
                cur.height = Math.max(cur.left != null ? cur.left.height : 0, cur.right != null ? cur.right.height : 0) + 1;
            }
            return maintain(cur);
        }



        // 普通搜索二叉树会有的基本方法


        public int size() {
            return size;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            return lastNode != null && key.compareTo(lastNode.key) == 0 ? true : false;
        }

        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.key) == 0) {
                lastNode.value = value;
            }
            else {
                size++;
                root = add(root, key, value);
            }
        }

        public void remove(K key) {
            if (key == null) {
                return;
            }
            if (containsKey(key)) {
                size--;
                root = delete(root, key);
            }
        }

        public V get(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.key) == 0) {
                return lastNode.value;
            }
            return null;
        }

        public K firstKey() {
            if (root == null) {
                return null;
            }
            AVLNode<K, V> cur = root;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur.key;
        }

        public K lastKey() {
            if (root == null) {
                return null;
            }
            AVLNode<K, V> cur = root;
            while (cur.right != null) {
                cur = cur.right;
            }
            return cur.key;
        }

        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
            return lastNoBigNode == null ? null : lastNoBigNode.key;
        }

        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
            return lastNoSmallNode == null ? null : lastNoSmallNode.key;
        }

        // todo: 这个函数代表什么意思呢？
        // 猜测：要不就找小于等于Key最大的，要不就找最后一个
        private AVLNode<K, V> findLastIndex(K key) {
            AVLNode<K, V> pre = root;
            AVLNode<K, V> cur = root;
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

        // 大于等于key最小的
        private AVLNode<K, V> findLastNoSmallIndex(K key) {
            AVLNode<K, V> res = null;
            AVLNode<K, V> cur = root;
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

        // 小于等于key最大的
        private AVLNode<K, V> findLastNoBigIndex(K key) {
            AVLNode<K, V> res = null;
            AVLNode<K, V> cur = root;
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
    }
}
